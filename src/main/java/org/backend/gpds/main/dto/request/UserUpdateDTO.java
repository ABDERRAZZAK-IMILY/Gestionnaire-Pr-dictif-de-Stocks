package org.backend.gpds.main.dto.request;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Email;

public class UserUpdateDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;
}
