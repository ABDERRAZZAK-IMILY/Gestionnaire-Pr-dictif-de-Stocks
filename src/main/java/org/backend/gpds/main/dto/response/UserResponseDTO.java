package org.backend.gpds.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.gpds.main.Enums.Role;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String login;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private boolean actif;

    private Long entrepotId;

}
