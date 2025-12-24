package org.backend.gpds.main.mapper;


import org.backend.gpds.main.dto.request.UserCreateDTO;
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

    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user;
    }
}
