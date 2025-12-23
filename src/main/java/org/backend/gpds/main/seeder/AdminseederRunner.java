package org.backend.gpds.main.seeder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.gpds.Enums.Role;
import org.backend.gpds.main.model.User;
import org.backend.gpds.main.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AdminseederRunner  implements CommandLineRunner {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        if (!userRepository.existsByEmail("admin@gpds.com")) {
            User admin = new User(
                    1L,
                    "Admin",
                    "admin@gpds.com",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
            );
            userRepository.save(admin);
            log.info(" Admin created: admin@gpds.com / admin123");
        }
    }
}
