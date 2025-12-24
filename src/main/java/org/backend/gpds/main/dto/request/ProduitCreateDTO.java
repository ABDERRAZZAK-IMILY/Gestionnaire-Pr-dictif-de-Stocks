package org.backend.gpds.main.dto.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.backend.gpds.main.Enums.Unite;

@Getter
@Setter
public class ProduitCreateDTO {

    private String nom;

    private String description;
    private String categorie;

    private double prixVente;

    private double prixAchat;

    private double marge;

    private double poids;

    @NotNull
    private Unite unite;
}
