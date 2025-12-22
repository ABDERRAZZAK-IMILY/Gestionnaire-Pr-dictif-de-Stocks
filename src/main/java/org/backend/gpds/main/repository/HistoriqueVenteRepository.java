package org.backend.gpds.main.repository;

import org.backend.gpds.main.model.HistoriqueVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueVenteRepository extends JpaRepository<HistoriqueVente, Long> {

    List<HistoriqueVente> findByEntrepotId(Long entrepotId);

    List<HistoriqueVente> findByProduitIdAndEntrepotId(
            Long produitId, Long entrepotId);
}
