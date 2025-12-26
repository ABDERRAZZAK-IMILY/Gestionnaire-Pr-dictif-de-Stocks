package org.backend.gpds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.backend.gpds.main.repository.jpa")
@EnableMongoRepositories(basePackages = "org.backend.gpds.main.repository.mongo")
public class GpdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpdsApplication.class, args);
    }


}
