package org.backend.gpds.main.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.backend.gpds.main.dto.request.VenteRequestDTO;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.model.HistoriqueVente;
import org.backend.gpds.main.model.Stock;
import org.backend.gpds.main.repository.jpa.EntrepotRepository;
import org.backend.gpds.main.repository.jpa.ProduitRepository;
import org.backend.gpds.main.repository.jpa.StockRepository;
import org.backend.gpds.main.repository.mongo.HistoriqueVenteRepository;
import org.backend.gpds.main.service.VenteService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VenteServiceImpl implements VenteService {

   private final  HistoriqueVenteRepository historiqueVenteRepository;
    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;
   private final  EntrepotRepository entrepotRepository;

    @Transactional
    public void processSale(VenteRequestDTO dto) {

        Stock stock = stockRepository.findByProduitIdAndEntrepotId(dto.getProduitId(), dto.getEntrepotId())
                .orElseThrow(() -> new ResourceNotFoundException("Stock non trouvé"));

        if (stock.getQuantiteDisponible() < dto.getQuantiteVendue()) {
            throw new RuntimeException("Stock insuffisant !");
        }

        stock.setQuantiteDisponible(stock.getQuantiteDisponible() - dto.getQuantiteVendue());
        stockRepository.save(stock);

        HistoriqueVente history = new HistoriqueVente();
        history.setProduitId(stock.getProduit().getId());
        history.setProduitNom(stock.getProduit().getNom());
        history.setEntrepotId(stock.getEntrepot().getId());
        history.setQuantiteVendue(dto.getQuantiteVendue());
        history.setDateVente(dto.getDateVondue());
        history.setJourSemaine(dto.getDateVondue().getDayOfWeek().toString());
        history.setMois(dto.getDateVondue().getMonthValue());
        history.setAnnee(dto.getDateVondue().getYear());

        historiqueVenteRepository.save(history);
    }



}
