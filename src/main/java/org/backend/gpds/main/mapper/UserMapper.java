package org.backend.gpds.main.mapper;


import org.backend.gpds.main.dto.auth.RegisterRequest;
import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest dto){
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

    public static UserResponseDTO toUserDTO(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

}
