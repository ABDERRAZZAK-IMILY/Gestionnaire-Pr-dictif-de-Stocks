    package org.backend.gpds.main.repository.mongo;

    import org.backend.gpds.main.model.HistoriqueVente;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import java.util.List;
    import java.util.Optional;

    public interface HistoriqueVenteRepository extends MongoRepository<HistoriqueVente, String> {
        List<HistoriqueVente> findByEntrepotId(Long entrepotId);
        List<HistoriqueVente> findByProduitIdAndEntrepotId(Long produitId, Long entrepotId);
    }
