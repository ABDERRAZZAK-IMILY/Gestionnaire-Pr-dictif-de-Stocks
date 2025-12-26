package org.backend.gpds.main.repository.jpa;


import org.backend.gpds.main.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByEntrepotId(Long entrepotId);

    Integer findQuantityByProductAndEntrepot(Long produitId, Long entrepotId);
}
