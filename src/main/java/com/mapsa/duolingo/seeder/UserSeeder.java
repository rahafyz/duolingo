/*
package com.mapsa.duolingo.seeder;

import com.github.javafaker.Faker;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Math.abs;

@Component
public class UserSeeder implements ApplicationRunner {

    private static final Faker faker=new Faker();
    private static final Random random=new Random();
    private UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            try {
                User user = User.builder().userName(faker.name().username()+i)
                        .firstName(faker.name().firstName()).lastName(faker.name().lastName())
                        .emailAddress(faker.name().firstName()+i*7+"@gmail.com").password(String.valueOf(abs(random.nextInt())))
                        .level(randomEnum(Level.class)).build();
                userRepository.save(user);
            }catch (RuntimeException e){
                continue;
            }


        }
    }
}
*/
