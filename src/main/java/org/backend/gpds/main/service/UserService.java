package org.backend.gpds.main.service;
import org.backend.gpds.main.dto.request.UserCreateDTO;
import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.dto.request.UserUpdateDTO;

import java.util.List;

public interface UserService {

    /**
     * ADMIN uniquement
     * Créer un utilisateur
     * - ADMIN : entrepot = null
     * - GESTIONNAIRE : entrepot obligatoire
     */
    UserResponseDTO createUser(UserCreateDTO dto);

    /**
     * ADMIN uniquement
     * Modifier un utilisateur
     */
    UserResponseDTO updateUser(Long userId, UserUpdateDTO dto);

    /**
     * ADMIN uniquement
     * Activer / désactiver un utilisateur
     */
    void toggleUserActivation(Long userId, boolean active);

    /**
     * ADMIN uniquement
     * Supprimer un utilisateur
     */
    void deleteUser(Long userId);

    /**
     * ADMIN uniquement
     * Liste de tous les utilisateurs
     */
    List<UserResponseDTO> getAllUsers();

    /**
     * ADMIN uniquement
     * Détails d’un utilisateur
     */
    UserResponseDTO getUserById(Long userId);

    /**
     * Utilisateur connecté
     * Récupérer son propre profil
     */
    UserResponseDTO getCurrentUserProfile(String login);
}
