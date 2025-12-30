package org.backend.gpds.main.service.impl;


import org.backend.gpds.main.dto.request.StockDTO;
import org.backend.gpds.main.dto.request.StockUpdateDTO;
import org.backend.gpds.main.exeptions.AccessEntrepotDeniedException;
import org.backend.gpds.main.mapper.StockMapper;
import org.backend.gpds.main.model.Entrepot;
import org.backend.gpds.main.model.Product;
import org.backend.gpds.main.model.Stock;

import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.repository.jpa.EntrepotRepository;
import org.backend.gpds.main.repository.jpa.ProduitRepository;
import org.backend.gpds.main.repository.jpa.StockRepository;
import lombok.RequiredArgsConstructor;
import org.backend.gpds.main.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;
    private final EntrepotRepository entrepotRepository;
    private final ProduitServiceImpl produitServiceImpl;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getStocksByEntrepot(Long entrepotId) {
        return stockRepository.findByEntrepotId(entrepotId);
    }

    @Override
    public Stock updateStock(Long stockId, StockUpdateDTO dto, Long entrepotUserId) {

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock introuvable"));

        if (!stock.getEntrepot().getId().equals(entrepotUserId)) {
            throw new AccessEntrepotDeniedException(
                    "Accès interdit : ce stock n'appartient pas à votre entrepôt"
            );
        }

        stock.setQuantiteDisponible(dto.getQuantiteDisponible());
        stock.setSeuilAlerte(dto.getSeuilAlerte());

        return stock;
    }

    @Override
    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock introuvable"));
    }

    public StockDTO initializeStock(StockDTO dto){
        Stock stock = StockMapper.toEntity(dto);

        Product product = produitRepository.findById(dto.getProduitId()).orElseThrow(
                ()->
                new ResourceNotFoundException("ther's not product with this id"));

        Entrepot entrepot = entrepotRepository.findById(dto.getEntrepotId()).orElseThrow(
                ()->new ResourceNotFoundException("ther's no entrepot with this id"));

        stock.setProduit(product);
        stock.setEntrepot(entrepot);
        return StockMapper.toDTO(stockRepository.save(stock));

    }
}
