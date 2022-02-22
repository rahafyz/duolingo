package com.mapsa.duolingo.admin;

import com.mapsa.duolingo.course.CourseDto;
import com.mapsa.duolingo.course.CourseMapper;
import com.mapsa.duolingo.course.ICourseService;
import com.mapsa.duolingo.language.ILanguageService;
import com.mapsa.duolingo.language.LanguageDto;
import com.mapsa.duolingo.language.LanguageMapper;
import com.mapsa.duolingo.level.ILevelService;
import com.mapsa.duolingo.level.LevelDto;
import com.mapsa.duolingo.level.LevelMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class adminController {
    ILevelService levelService;
    ILanguageService languageService;
    ICourseService courseService;

    LevelMapper levelMapper;
    LanguageMapper languageMapper;
    CourseMapper courseMapper;

    @PostMapping(value = "/add-Course")
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto){
        CourseDto newCourse = courseMapper.toDto(courseService.save(courseMapper.toEntity(courseDto)));
        return new ResponseEntity(newCourse, HttpStatus.OK);
    }

    @PostMapping(value = "/add-Language")
    public ResponseEntity<LanguageDto> saveLanguage(@RequestBody LanguageDto languageDto){
        LanguageDto newLanguage = languageMapper.toDto(languageService.save(languageMapper.toEntity(languageDto)));
        return new ResponseEntity(newLanguage, HttpStatus.OK);
    }

    @PostMapping(value = "/add-Level")
    public ResponseEntity<LevelDto> saveLevel(@RequestBody LevelDto levelDto){
        LevelDto newLevel = levelMapper.toDto(levelService.save(levelMapper.toEntity(levelDto)));
        return new ResponseEntity(newLevel, HttpStatus.OK);
    }

}
