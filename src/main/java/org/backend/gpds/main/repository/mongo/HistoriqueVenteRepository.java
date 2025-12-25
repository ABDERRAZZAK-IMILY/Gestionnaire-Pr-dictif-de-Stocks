package org.backend.gpds.main.repository.mongo;

import org.backend.gpds.main.model.HistoriqueVente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueVenteRepository extends MongoRepository<HistoriqueVente,String> {
}
