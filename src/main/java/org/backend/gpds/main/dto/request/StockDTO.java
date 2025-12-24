package org.backend.gpds.main.dto.request;


import lombok.*;

@Getter @Setter
public class StockDTO {
    private Long id;
    private String produitNom;
    private int quantiteDisponible;
    private int seuilAlerte;
    private boolean stockBas;
}
