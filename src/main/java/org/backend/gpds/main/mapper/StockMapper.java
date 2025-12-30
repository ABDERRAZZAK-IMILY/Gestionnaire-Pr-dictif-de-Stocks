package org.backend.gpds.main.mapper;

import org.backend.gpds.main.dto.request.StockDTO;
import org.backend.gpds.main.model.Stock;

public class StockMapper {

    public static StockDTO toDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setProduitId(stock.getProduit().getId()); // Extract ID
        dto.setEntrepotId(stock.getEntrepot().getId()); // Extract ID
        dto.setQuantiteDisponible(stock.getQuantiteDisponible());
        dto.setSeuilAlerte(stock.getSeuilAlerte());


        dto.setStockBas(stock.getQuantiteDisponible() <= stock.getSeuilAlerte());
        return dto;
    }

    public static Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setQuantiteDisponible(dto.getQuantiteDisponible());
        stock.setSeuilAlerte(dto.getSeuilAlerte());

        return stock;
    }
}