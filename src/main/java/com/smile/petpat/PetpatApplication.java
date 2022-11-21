package com.smile.petpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetpatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpatApplication.class, args);
    }

}
