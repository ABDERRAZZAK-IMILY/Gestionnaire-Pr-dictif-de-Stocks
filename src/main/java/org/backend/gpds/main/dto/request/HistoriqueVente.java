package org.backend.gpds.main.dto.request;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class HistoriqueVente {



    private Long productId;
    private Long entrepotId;
    private Integer quantiteVendue;
    private LocalDateTime dateVente;

}
