package org.backend.gpds.main.repository.jpa;

import org.backend.gpds.main.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  org.backend.gpds.Enums.Role;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Utilisé pour l'authentification JWT
     */
    Optional<User> findByLogin(String login);

    /**
     * Vérifier unicité du login
     */
    boolean existsByLogin(String login);

    /**
     * Vérifier unicité de l'email
     */
    boolean existsByEmail(String email);

    /**
     * ADMIN : voir tous les gestionnaires
     */
    List<User> findByRole(Role role);

    /**
     * ADMIN : voir les gestionnaires par entrepôt
     */
    List<User> findByRoleAndEntrepotId(Role role, Long entrepotId);
}
