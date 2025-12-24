package org.backend.gpds.main.dto.request;


import org.backend.gpds.Enums.Unite;
import lombok.*;

@Getter @Setter
public class ProduitGestionnaireDTO {
    private Long id;
    private String nom;
    private String description;
    private String categorie;
    private double prixVente;
    private double poids;
    private Unite unite;
}
