package org.backend.gpds.main.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockUpdateDTO {

    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private int quantiteDisponible;

    @Min(value = 0, message = "Le seuil d’alerte ne peut pas être négatif")
    private int seuilAlerte;
}
