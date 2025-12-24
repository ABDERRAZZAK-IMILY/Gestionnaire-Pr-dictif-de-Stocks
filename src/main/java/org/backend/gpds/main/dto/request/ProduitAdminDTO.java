package org.backend.gpds.main.dto.request;


import org.backend.gpds.Enums.Unite;
import lombok.*;

@Getter @Setter
public class ProduitAdminDTO {
    private Long id;
    private String nom;
    private String description;
    private String categorie;
    private double prixVente;
    private double prixAchat;
    private double marge;
    private double poids;
    private Unite unite;
    private boolean actif;

}
