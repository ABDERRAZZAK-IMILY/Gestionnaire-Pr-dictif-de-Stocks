package org.backend.gpds.main.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historique_ventes")
public class HistoriqueVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Product produit;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;

    @Column(nullable = false)
    private LocalDate dateVente;

    @Column(nullable = false)
    private Integer quantiteVendue;

    @Column(nullable = false)
    private String jourSemaine;

    private Integer mois;
    private Integer annee;
}
