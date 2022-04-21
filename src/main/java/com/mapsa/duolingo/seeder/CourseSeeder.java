/*
package com.mapsa.duolingo.seeder;

import com.github.javafaker.Faker;
import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseRepository;
import com.mapsa.duolingo.language.LanguageService;
import com.mapsa.duolingo.level.Level;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CourseSeeder implements ApplicationRunner {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private LanguageService languageService;

    private CourseRepository repository;

    public CourseSeeder(LanguageService languageService, CourseRepository repository) {
        this.languageService = languageService;
        this.repository = repository;
    }

    private <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 50000; i++) {
            try {
                Course course = Course.builder().name(faker.name().title()).language(languageService.getById(random.nextLong(1, 15)))
                        .level(randomEnum(Level.class)).build();
                repository.save(course);


            } catch (RuntimeException e) {
                continue;
            }
        }
    }
}
*/
