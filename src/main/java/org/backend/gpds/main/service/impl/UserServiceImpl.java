package org.backend.gpds.main.service.impl;

import lombok.RequiredArgsConstructor;
import org.backend.gpds.main.Enums.Role;
import org.backend.gpds.main.dto.request.UserCreateDTO;
import org.backend.gpds.main.dto.request.UserUpdateDTO;
import org.backend.gpds.main.dto.response.UserResponseDTO;
import org.backend.gpds.main.exeptions.InvalidUserRoleException;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.mapper.UserMapper;
import org.backend.gpds.main.model.Entrepot;
import org.backend.gpds.main.model.User;
import org.backend.gpds.main.repository.jpa.EntrepotRepository;
import org.backend.gpds.main.repository.jpa.UserRepository;
import org.backend.gpds.main.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    @Transactional
    public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final EntrepotRepository entrepotRepository;
        private final UserMapper userMapper;
        private final PasswordEncoder passwordEncoder;

        @Override
        public UserResponseDTO createUser(UserCreateDTO dto) {

            if (dto.getRole() == Role.GESTIONNAIRE && dto.getEntrepotId() == null) {
                throw new InvalidUserRoleException("Un gestionnaire doit être assigné à un entrepôt");
            }

            Entrepot entrepot = null;
            if (dto.getRole() == Role.GESTIONNAIRE) {
                entrepot = entrepotRepository.findById(dto.getEntrepotId())
                        .orElseThrow(() -> new ResourceNotFoundException("Entrepôt introuvable"));
            }

            User user = User.builder()
                    .login(dto.getLogin())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .nom(dto.getNom())
                    .prenom(dto.getPrenom())
                    .email(dto.getEmail())
                    .role(dto.getRole())
                    .actif(true)
                    .entrepot(entrepot)
                    .build();

            return userMapper.toDto(userRepository.save(user));
        }

        @Override
        public UserResponseDTO updateUser(Long userId, UserUpdateDTO dto) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

            user.setNom(dto.getNom());
            user.setPrenom(dto.getPrenom());
            user.setEmail(dto.getEmail());

            return userMapper.toDto(user);
        }

        @Override
        public void toggleUserActivation(Long userId, boolean actif) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));
            user.setActif(actif);
        }

        @Override
        public void deleteUser(Long userId) {
            if (!userRepository.existsById(userId)) {
                throw new ResourceNotFoundException("Utilisateur introuvable");
            }
            userRepository.deleteById(userId);
        }

        @Override
        @Transactional(readOnly = true)
        public List<UserResponseDTO> getAllUsers() {
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
        }

        @Override
        @Transactional(readOnly = true)
        public UserResponseDTO getUserById(Long userId) {
            return userRepository.findById(userId)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));
        }

        @Override
        @Transactional(readOnly = true)
        public UserResponseDTO getCurrentUserProfile(String login) {
            User user = userRepository.findByLogin(login)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));
            return userMapper.toDto(user);
        }
    }
