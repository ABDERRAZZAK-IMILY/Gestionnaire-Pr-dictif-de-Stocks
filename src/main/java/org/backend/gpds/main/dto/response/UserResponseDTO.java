package org.backend.gpds.main.dto.response;

import org.backend.gpds.Enums.Role;

public class UserResponseDTO {

    private Long id;
    private String login;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private boolean active;
    private Long entrepotId;
}
