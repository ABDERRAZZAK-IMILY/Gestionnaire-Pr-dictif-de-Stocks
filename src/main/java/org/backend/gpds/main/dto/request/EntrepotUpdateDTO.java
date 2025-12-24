package org.backend.gpds.main.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EntrepotUpdateDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String ville;

    @NotBlank
    private String adresse;

    private boolean actif;
}
