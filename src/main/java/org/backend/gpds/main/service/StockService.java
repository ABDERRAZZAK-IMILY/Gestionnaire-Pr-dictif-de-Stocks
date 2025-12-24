package org.backend.gpds.main.service;

import org.backend.gpds.main.dto.request.StockUpdateDTO;
import org.backend.gpds.main.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks(); // ADMIN

    List<Stock> getStocksByEntrepot(Long entrepotId);

    Stock updateStock(Long stockId, StockUpdateDTO dto, Long entrepotUserId);

    Stock getStockById(Long stockId);
}
