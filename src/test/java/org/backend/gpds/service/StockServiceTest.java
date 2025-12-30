package org.backend.gpds.service;

import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.model.Stock;
import org.backend.gpds.main.repository.jpa.StockRepository;
import org.backend.gpds.main.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {


    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;


    @Test
    public void testGetAllStocks() {

        List<Stock> stocks = List.of(new Stock() , new Stock());

        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> result = stockService.getAllStocks();

        assertEquals(stocks, result);

        verify(stockRepository).findAll();

    }


    @Test
    public void testGetStocksByEntrepot() {
        Long id = 1L;
        List<Stock> stocks = List.of(new Stock(), new Stock());

        when(stockRepository.findByEntrepotId(id)).thenReturn(stocks);

        List<Stock> result = stockService.getStocksByEntrepot(id);

        assertEquals(stocks, result);
        verify(stockRepository).findByEntrepotId(id);
    }

    @Test
    public void testGetStockById_Success() {
        Long id = 1L;
        Stock stock = new Stock();

        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        Stock result = stockService.getStockById(id);

        assertEquals(stock, result);
        verify(stockRepository).findById(id);
    }

    @Test
    public void testGetStockById_NotFound() {
        Long stockId = 1L;

        when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> stockService.getStockById(stockId));

        verify(stockRepository).findById(stockId);
    }


}
