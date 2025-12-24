package org.backend.gpds.main.dto.response;


import jakarta.validation.constraints.Min;
import lombok.*;

@Getter @Setter
public class StockUpdateDTO {

    @Min(0)
    private int quantiteDisponible;

    @Min(0)
    private int seuilAlerte;
}
