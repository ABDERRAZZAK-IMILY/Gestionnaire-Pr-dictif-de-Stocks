package org.backend.gpds.main.model;

import org.backend.gpds.main.model.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "stocks",
        uniqueConstraints = @UniqueConstraint(columnNames = {"produit_id", "entrepot_id"}))
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product produit;

    @ManyToOne(optional = false)
    private Entrepot entrepot;

    @Column(nullable = false)
    private int quantiteDisponible;

    @Column(nullable = false)
    private int seuilAlerte;
}