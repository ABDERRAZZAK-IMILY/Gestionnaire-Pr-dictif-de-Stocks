package org.backend.gpds.main.dto.request;


import org.backend.gpds.Enums.Role;
import jakarta.validation.constraints.*;

public class UserCreateDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;

    @NotNull
    private Role role;

    // obligatoire si role = GESTIONNAIRE
    private Long entrepotId;
}
