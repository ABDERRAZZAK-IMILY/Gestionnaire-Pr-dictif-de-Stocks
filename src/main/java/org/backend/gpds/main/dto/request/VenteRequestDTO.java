package org.backend.gpds.main.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
public class VenteRequestDTO {
    @NotNull
    private Long produitId;
    @NotNull
    private Long entrepotId;

    @Min(value = 1)
    private int quantiteVendue;

    private LocalDate  dateVondue = LocalDate.now();
}
