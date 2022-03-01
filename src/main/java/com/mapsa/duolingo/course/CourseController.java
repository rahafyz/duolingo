package com.mapsa.duolingo.course;

import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserDto;
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


    @PostMapping(value = "POST /course")
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        CourseDto newCourse = mapper.toDto(courseService.save(mapper.toEntity(courseDto)));
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping(value = "/GET /courses")
    public ResponseEntity<CourseDto> getAllCourses() {
        List<Course> courses = courseService.getAll();
        return new ResponseEntity(mapper.toListDto(courses), HttpStatus.OK);
    }

    /*@GetMapping(value = "/languages")
    public ResponseEntity<List<Language>> getAllLangs(){
        return new ResponseEntity(Arrays.asList(Language.values()),HttpStatus.OK);
    }
*/

    @GetMapping(value = "/GET /course/")
    public ResponseEntity<List<CourseDto>> getByLanguage(@RequestParam Long languageId) {
        List<Course> courses = courseService.getByLang(languageId);
        return ResponseEntity.ok(mapper.toListDto(courses));
    }


    @GetMapping(value = "/GET /users/")
    public ResponseEntity<List<UserDto>> getUsersByCourse(@RequestParam Long courseId) {
        List<User> users = courseUserService.getUsersByCourse(courseId);
        return ResponseEntity.ok(userMapper.toListDto(users));
    }

}
