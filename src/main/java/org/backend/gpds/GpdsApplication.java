package org.backend.gpds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        exclude = {
                org.springframework.ai.model.deepseek.autoconfigure.DeepSeekChatAutoConfiguration.class
        }
)
public class GpdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpdsApplication.class, args);
    }


}
