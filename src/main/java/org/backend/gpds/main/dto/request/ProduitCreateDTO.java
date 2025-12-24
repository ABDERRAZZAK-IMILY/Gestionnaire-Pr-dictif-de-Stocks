package org.backend.gpds.main.dto.request;


import org.backend.gpds.Enums.Unite;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitCreateDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String description;

    private String categorie;

    @Positive(message = "Le prix de vente doit être positif")
    private double prixVente;

    @Positive(message = "Le prix d'achat doit être positif")
    private double prixAchat;

    @PositiveOrZero(message = "La marge ne peut pas être négative")
    private double marge;

    @Positive(message = "Le poids doit être positif")
    private double poids;

    @NotNull
    private Unite unite;
}
