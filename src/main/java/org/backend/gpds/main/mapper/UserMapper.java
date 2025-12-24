package org.backend.gpds.main.mapper;


import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .role(user.getRole())
                .entrepotId(user.getEntrepot() != null ? user.getEntrepot().getId() : null)
                .build();
    }
}
