package com.mapsa.duolingo.util;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseDto;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.user.User;

import static com.mapsa.duolingo.util.LanguageData.language;

public class UserData {
    public static User user(int i) {
        return User.builder()
                .firstName("firstName"+i).lastName("lastName"+i)
                .userName("username"+i).emailAddress("email"+i).password("pass"+i)
                .level(Level.of(i)).build();
    }

    public static CourseDto courseDto(int i) {
        return CourseDto.builder()
                .name("course" + i)
                .language(language(0))
                .level(Level.of(i))
                .build();
    }
}
