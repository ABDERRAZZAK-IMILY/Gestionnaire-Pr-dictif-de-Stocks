package org.backend.gpds.main.controller;


import org.backend.gpds.main.dto.request.StockDTO;
import org.backend.gpds.main.dto.request.StockUpdateDTO;
import org.backend.gpds.main.mapper.StockMapper;
import org.backend.gpds.main.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // ADMIN : voir tous les stocks
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StockDTO> getAllStocks() {
        return stockService.getAllStocks()
                .stream()
                .map(StockMapper::toDTO)
                .toList();
    }

    // ADMIN ou GESTIONNAIRE (son entrepôt)
    @GetMapping("/entrepot/{entrepotId}")
    @PreAuthorize("hasAnyRole('ADMIN','GESTIONNAIRE')")
    public List<StockDTO> getByEntrepot(@PathVariable Long entrepotId) {
        return stockService.getStocksByEntrepot(entrepotId)
                .stream()
                .map(StockMapper::toDTO)
                .toList();
    }

    // GESTIONNAIRE : mise à jour stock de SON entrepôt
    @PutMapping("/{stockId}")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public StockDTO updateStock(
            @PathVariable Long stockId,
            @RequestParam Long entrepotUserId, // issu du JWT normalement
            @RequestBody @Valid StockUpdateDTO dto) {

        return StockMapper.toDTO(
                stockService.updateStock(stockId, dto, entrepotUserId)
        );
    }
}
