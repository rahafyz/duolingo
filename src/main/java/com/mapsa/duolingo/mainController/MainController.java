package com.mapsa.duolingo.mainController;

import com.mapsa.duolingo.course.*;
import com.mapsa.duolingo.language.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private ILanguageService languageService;
    private LanguageMapper langMapper;
    private ICourseService courseService;
    private CourseMapper courseMapper;


    @GetMapping(value = "/courses")
    public ResponseEntity<CourseDto> getAllCourses(){
        List<Course> courses = courseService.getAll();
        return new ResponseEntity(courseMapper.toListDto(courses), HttpStatus.OK);
    }

    @GetMapping(value = "/languages")
    public ResponseEntity<LanguageDto> getAllLanguages(){
        List<Language> languages = languageService.getAll();
        return new ResponseEntity(langMapper.toListDto(languages), HttpStatus.OK);
    }
}
