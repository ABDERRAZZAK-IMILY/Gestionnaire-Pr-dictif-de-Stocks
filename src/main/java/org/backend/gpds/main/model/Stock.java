package org.backend.gpds.main.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Entrepot entrepot;

    private int quantiteDisponible;

    private int seuilAlerte;
}
