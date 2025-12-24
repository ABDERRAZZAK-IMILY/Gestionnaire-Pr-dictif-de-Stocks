package org.backend.gpds.main.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EntrepotCreateDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
}
