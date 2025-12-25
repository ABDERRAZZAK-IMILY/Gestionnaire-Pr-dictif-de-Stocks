package org.backend.gpds.main.service;
import org.backend.gpds.main.dto.request.UserCreateDTO;
import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.dto.request.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO dto);

    UserResponseDTO updateUser(Long userId, UserUpdateDTO dto);

    void toggleUserActivation(Long userId, boolean actif);

    void deleteUser(Long userId);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long userId);

    UserResponseDTO getCurrentUserProfile(String login);
}