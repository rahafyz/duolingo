package com.mapsa.duolingo.util;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseDto;
import com.mapsa.duolingo.level.Level;

import java.util.ArrayList;
import java.util.List;

import static com.mapsa.duolingo.util.LanguageData.language;

public class CourseData {
    public static Course course(int i) {
        return Course.builder()
                .name("course" + i)
                .language(language(i))
                .level(Level.of(i))
                .build();
    }

    public static CourseDto courseDto(int i) {
        return CourseDto.builder()
                .name("course" + i)
                .language(language(0))
                .level(Level.of(i))
                .build();
    }

    public static List<CourseDto> courseDtoList() {
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            courseDtoList.add(CourseDto.builder()
                    .name("course" + i)
                    .language(language(i))
                    .level(Level.of(i))
                    .build());
        }
        return courseDtoList;
    }
}
