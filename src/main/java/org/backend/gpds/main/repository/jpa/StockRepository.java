package org.backend.gpds.main.repository.jpa;


import org.backend.gpds.main.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByEntrepotId(Long entrepotId);

    @Query("SELECT s.quantiteDisponible FROM Stock s WHERE s.produit.id = :produitId AND s.entrepot.id = :entrepotId")
    Integer findQuantityByProductAndEntrepot(@Param("produitId") Long produitId, @Param("entrepotId") Long entrepotId);}
