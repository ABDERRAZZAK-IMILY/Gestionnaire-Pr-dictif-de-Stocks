package org.backend.gpds.main.service.impl;

import com.ethlo.time.DateTime;
import org.backend.gpds.main.dto.request.HistoriqueVente;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.model.Prevision;
import org.backend.gpds.main.repository.jpa.StockRepository;
import org.backend.gpds.main.repository.mongo.HistoriqueVenteRepository;
import org.backend.gpds.main.repository.mongo.PrevisionRepository;
import org.springframework.ai.chat.client.ChatClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

public class AiServiceImpl {

    private ChatClient chatClient;

    private  HistoriqueVenteRepository historiqueVenteRepository;
    private  PrevisionRepository previsionRepository;
    private  StockRepository stockRepository;


    public AiServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public Prevision GeneratePredictions(Long productId ,Long entrepotId){

        List<HistoriqueVente> history = historiqueVenteRepository.findByProductIdAndEntrepotId(productId,entrepotId).orElseThrow(()
                -> new ResourceNotFoundException("THER'S no History for this productId and entrepotId"));

            Integer currentStock =stockRepository.findByProductIdAndIntrepotId(productId,entrepotId).orElseThtrow(()->new ResourceNotFoundException("THER'S no History for this productId and entrepotId"));

            double dailySpeed = calculateAverageSpeed(history);
            int daysUntilStockOut = (dailySpeed > 0) ? (int) (currentStock/dailySpeed): 999;

            int neededFor30Days =(int) (dailySpeed * 30);

            String salesContexts = history.stream().limit(10)
                    .map(h->String.format("- %s: %d units", h.getDateVente().toLocalDate(), h.getQuantiteVendue()))
                    .collect(Collectors.joining("\n"));

                             String prompt = String.format("""
                   Act as an inventory expert.

                  DATA:
                      - Stock: %d
                          - Daily Sales: %.1f
                      - Days until empty: %d

                        HISTORY (Last 10 sales):
                         %s

                             TASK:
                         Write a 1-sentence recommendation for the manager.
                         If days until empty is < 5, start with "URGENT ORDER NEEDED".
                        Otherwise, suggest a standard restock strategy.
                 """,
                currentStock, dailySpeed, daysUntilStockOut, salesContexts);

        String aiRecommendation = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        // --- 6. SAVE ---
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


    public double calculateAverageSpeed(List<HistoriqueVente>history){
        if(history.isEmpty()){
            return 0.0;
        }
        int totalSold =history.stream().mapToInt(HistoriqueVente::getQuantiteVendue).sum();
        history.sort((a, b)->a.getDateVente().compareTo(b.getDateVente()));

        LocalDateTime firstDate = history.get(0).getDateVente();
        long daysDiff = ChronoUnit.DAYS.between(firstDate.LocalDateTime.now());
        if (daysDiff == 0) daysDiff = 1;
        return (double) totalSold / daysDiff;
    }




}
