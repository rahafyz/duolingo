package com.mapsa.duolingo.course;

import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@AllArgsConstructor
public class CourseController {

    private ICourseService courseService;
    private ICourseUserService courseUserService;
    private CourseMapper mapper;
    private UserMapper userMapper;






    //ResponseEntity<--->
    @GetMapping(value = "/courses")
    public ResponseEntity<CourseDto> getByLanguage(@RequestParam Long languageId) {
        List<Course> courses = courseService.getByLang(languageId);
        return new ResponseEntity(mapper.toListDto(courses), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<User> getUsersByCourse(@RequestParam Long courseId){
        List<User> users = courseUserService.getUsersByCourse(courseId);
        return new ResponseEntity(userMapper.toListDto(users),HttpStatus.OK);
    }

}
