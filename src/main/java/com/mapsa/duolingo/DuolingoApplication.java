package com.mapsa.duolingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DuolingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuolingoApplication.class, args);
    }

}
