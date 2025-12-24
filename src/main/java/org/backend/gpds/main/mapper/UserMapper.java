package org.backend.gpds.main.mapper;


import org.backend.gpds.main.dto.auth.RegisterRequest;
import org.backend.gpds.main.dto.request.UserCreateDTO;
import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.model.User;
import org.springframework.stereotype.Component;
import org.backend.gpds.main.Enums.Role;

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

    // Pour UserCreateDTO
    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    // Pour RegisterRequest (ajout)
    public User toEntity(RegisterRequest dto) {
        User user = new User();
        user.setNom(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole().name()));
        user.setActif(true); // par défaut actif
        return user;
    }

}
