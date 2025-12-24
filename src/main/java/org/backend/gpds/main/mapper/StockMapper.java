package org.backend.gpds.main.mapper;

import org.backend.gpds.main.dto.request.StockDTO;
import org.backend.gpds.main.model.Stock;

public class StockMapper {

    public static StockDTO toDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setProduitNom(stock.getProduit().getNom());
        dto.setQuantiteDisponible(stock.getQuantiteDisponible());
        dto.setSeuilAlerte(stock.getSeuilAlerte());
        dto.setStockBas(stock.getQuantiteDisponible() <= stock.getSeuilAlerte());
        return dto;
    }
}
