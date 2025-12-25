package org.backend.gpds.main.repository.mongo;

import org.backend.gpds.main.model.HistoriqueVente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistoriqueVenteRepository extends MongoRepository<HistoriqueVente, String> {
    List<HistoriqueVente> findByEntrepotId(Long entrepotId);
}