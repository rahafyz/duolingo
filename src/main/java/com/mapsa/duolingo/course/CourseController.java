package com.mapsa.duolingo.course;

import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    private CourseService courseService;
    private CourseUserService courseUserService;
    private CourseMapper mapper;
    private UserMapper userMapper;

    public CourseController(CourseService courseService, CourseUserService courseUserService, CourseMapper mapper, UserMapper userMapper) {
        this.courseService = courseService;
        this.courseUserService = courseUserService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/all-courses")
    public ResponseEntity<CourseDto> getAll(){
        List<Course> courses = courseService.getAll();
        return new ResponseEntity(mapper.toListDto(courses), HttpStatus.OK);
    }


    //ResponseEntity<--->
    @GetMapping(value = "/courses")
    public ResponseEntity<CourseDto> getByLanguage(@RequestParam Long languageId) {
        List<Course> courses = courseService.getByLang(languageId);
        return new ResponseEntity(mapper.toListDto(courses), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<User> getUsersCourse(@RequestParam Long courseId){
        List<User> users = courseUserService.getUsersByCourse(courseId);
        return new ResponseEntity(userMapper.toListDto(users),HttpStatus.OK);
    }

}
