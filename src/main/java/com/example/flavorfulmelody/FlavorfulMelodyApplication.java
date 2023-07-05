package com.example.flavorfulmelody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlavorfulMelodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlavorfulMelodyApplication.class, args);
    }

}
