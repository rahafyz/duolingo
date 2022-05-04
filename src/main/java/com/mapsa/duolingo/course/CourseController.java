package com.mapsa.duolingo.course;

import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserDto;
import com.mapsa.duolingo.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;
    private CourseMapper mapper;
    private UserMapper userMapper;

    @Autowired
    public CourseController(ICourseService courseService, CourseMapper mapper, UserMapper userMapper) {
        this.courseService = courseService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/course")
    public ResponseEntity<Void> saveCourse(@RequestBody CourseDto courseDto) {
        mapper.toDto(courseService.save(mapper.toEntity(courseDto)));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseService.getAll();
        return ResponseEntity.ok(mapper.toListDto(courses));
    }

    /*@GetMapping(value = "/languages")
    public ResponseEntity<List<Language>> getAllLangs(){
        return new ResponseEntity(Arrays.asList(Language.values()),HttpStatus.OK);
    }
*/


    @GetMapping(value = "/course/")
    public ResponseEntity<List<CourseDto>> getByLanguage(@RequestParam Long languageId) {
        List<Course> courses = courseService.getByLang(languageId);
        return ResponseEntity.ok(mapper.toListDto(courses));
    }


    @GetMapping(value = "/users/")
    public ResponseEntity<List<UserDto>> getUsersByCourse(@RequestParam Long courseId) {
        List<User> users = courseService.users(courseId);
        return ResponseEntity.ok(userMapper.toListDto(users));
    }

}
