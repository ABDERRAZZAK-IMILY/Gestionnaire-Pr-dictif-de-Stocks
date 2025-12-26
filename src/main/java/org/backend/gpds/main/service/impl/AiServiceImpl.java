package org.backend.gpds.main.service.impl;

import org.backend.gpds.main.model.HistoriqueVente;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.model.Prevision;
import org.backend.gpds.main.repository.jpa.StockRepository;
import org.backend.gpds.main.repository.mongo.HistoriqueVenteRepository;
import org.backend.gpds.main.repository.mongo.PrevisionRepository;
import org.backend.gpds.main.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;
    private final HistoriqueVenteRepository historiqueVenteRepository;
    private final PrevisionRepository previsionRepository;
    private final StockRepository stockRepository;


    public AiServiceImpl(ChatClient.Builder builder,
                         HistoriqueVenteRepository historiqueVenteRepository,
                         PrevisionRepository previsionRepository,
                         StockRepository stockRepository) {
        this.chatClient = builder.build();
        this.historiqueVenteRepository = historiqueVenteRepository;
        this.previsionRepository = previsionRepository;
        this.stockRepository = stockRepository;
    }

    public Prevision generatePredictions(Long productId, Long entrepotId) {

        List<HistoriqueVente> history = historiqueVenteRepository
                .findByProduitIdAndEntrepotId(productId, entrepotId);


        Integer currentStock = stockRepository.findQuantityByProductAndEntrepot(productId, entrepotId);
        if (currentStock == null) throw new ResourceNotFoundException("Stock not found");

        double dailySpeed = calculateAverageSpeed(history);


        if (history.isEmpty()) return null;

        int daysUntilStockOut = (dailySpeed > 0) ? (int) (currentStock / dailySpeed) : 999;
        int neededFor30Days = (int) (dailySpeed * 30);

        String salesContexts = history.stream().limit(10)
                .map(h -> String.format("- %s: %d units", h.getDateVente(), h.getQuantiteVendue()))
                .collect(Collectors.joining("\n"));

        String prompt = String.format("""
               Act as an inventory expert.
        
               DATA:
                  - Stock: %d
                  - Daily Sales: %.1f
                  - "Days until empty: %d (Estimated Date: %s)"
        
               HISTORY (Last 10 sales):
               %s
         
               TASK:
               Write a 1-sentence recommendation for the manager.
               If days until empty is < 5, start with "URGENT ORDER NEEDED".
               Otherwise, suggest a standard restock strategy.
             """,
                currentStock, dailySpeed, daysUntilStockOut, salesContexts,LocalDate.now().plusDays(daysUntilStockOut));

        String aiRecommendation = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        Prevision prevision = Prevision.builder()
                .productId(productId)
                .EntrepotId(entrepotId)
                .datePrevision(LocalDate.now())
                .quantitePrevue30Jours(neededFor30Days)
                .niveauConfiance(85.0)
                .recommandation(aiRecommendation)
                .build();

        return previsionRepository.save(prevision);
    }

    public double calculateAverageSpeed(List<HistoriqueVente> history) {
        if (history.isEmpty()) {
            return 0.0;
        }
        int totalSold = history.stream().mapToInt(HistoriqueVente::getQuantiteVendue).sum();

        history.sort((a, b) -> a.getDateVente().compareTo(b.getDateVente()));


        LocalDate firstDate = history.get(0).getDateVente();


        long daysDiff = ChronoUnit.DAYS.between(firstDate, LocalDateTime.now());

        if (daysDiff == 0) daysDiff = 1;
        return (double) totalSold / daysDiff;
    }
}