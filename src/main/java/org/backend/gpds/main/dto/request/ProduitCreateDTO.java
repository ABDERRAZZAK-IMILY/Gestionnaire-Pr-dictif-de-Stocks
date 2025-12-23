package org.backend.gpds.main.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
public class ProduitCreateDTO {

    @NotBlank
    private String nom;

    private String description;
    private String categorie;

    @Positive
    private double prixVente;

    @Positive
    private double prixAchat;

    @PositiveOrZero
    private double marge;

    @Positive
    private double poids;

    @NotNull
    private Unite unite;
}
