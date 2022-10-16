package com.mapsa.duolingo;

import com.mapsa.duolingo.config.FileStorageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@EnableCaching
@EnableRabbit
public class DuolingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuolingoApplication.class, args);
    }

}
