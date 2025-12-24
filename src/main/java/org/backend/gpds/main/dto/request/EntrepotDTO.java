package org.backend.gpds.main.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EntrepotDTO {

    private Long id;
    private String nom;
    private String ville;
    private String adresse;
    private boolean actif;
}
