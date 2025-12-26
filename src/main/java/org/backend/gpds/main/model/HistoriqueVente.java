package org.backend.gpds.main.model;


import lombok.Data;

import java.time.LocalDate;
@Data
public class HistoriqueVente {

    private String id;
    private Long EntrepotId;
    private LocalDate date_vente;
    private Integer quantity_vondue;
    private String jour_semaine;
    private Integer annee;

}
