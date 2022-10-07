/*
package com.mapsa.duolingo.seeder;

import com.mapsa.duolingo.courseUser.CourseUserKey;
import com.mapsa.duolingo.courseUser.CourseUserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CUSeeder implements ApplicationRunner {

    private CourseUserService service;
    private static final Random random = new Random();

    public CUSeeder(CourseUserService service) {
        this.service = service;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100000; i++) {
                try {
                    CourseUserKey cu = CourseUserKey.builder().userId(random.nextLong(2999, 102090))
                            .courseId(random.nextLong(2, 75453)).build();

                    service.save(cu);
                }catch (RuntimeException e){
                    continue;
                }
        }
    }
}
*/
