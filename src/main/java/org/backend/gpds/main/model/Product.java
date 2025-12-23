package org.backend.gpds.main.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String categorie;

    private double prixVente;

    private double prixAchat; // ADMIN uniquement

    private double marge;     // ADMIN uniquement

    private double poids;

    @Enumerated(EnumType.STRING)
    private Unite unite;

    private boolean actif = true;
}
