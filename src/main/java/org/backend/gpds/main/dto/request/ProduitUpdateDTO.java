package org.backend.gpds.main.dto.request;

import org.backend.gpds.Enums.Unite;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitUpdateDTO {

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

    private boolean actif;
}
