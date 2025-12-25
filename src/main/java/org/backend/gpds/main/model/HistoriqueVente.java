package org.backend.gpds.main.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "historique_ventes")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class HistoriqueVente {

    @Id
    private String id;

    private Long produitId;
    private Long entrepotId;

    private String produitNom;
    private String entrepotNom;

    private int quantiteVendue;
    private LocalDate dateVente;

    private String jourSemaine;
    private int mois;
    private int annee;
}