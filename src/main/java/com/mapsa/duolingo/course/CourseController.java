package com.mapsa.duolingo.course;

import com.mapsa.duolingo.user.User;
import com.mapsa.duolingo.user.UserDto;
import com.mapsa.duolingo.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;
    private final CourseMapper mapper;
    private final UserMapper userMapper;

    /*@Autowired
    public CourseController(ICourseService courseService, CourseMapper mapper, UserMapper userMapper) {
        this.courseService = courseService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }*/

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody CourseDto courseDto) {
        mapper.toDto(courseService.save(mapper.toEntity(courseDto)));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseService.getAll();
        return ResponseEntity.ok(mapper.toListDto(courses));
    }


    @GetMapping()
    public ResponseEntity<List<CourseDto>> getByLanguage(@RequestParam("languageId") Long languageId) {
        List<Course> courses = courseService.getByLang(languageId);
        return ResponseEntity.ok(mapper.toListDto(courses));
    }


    @GetMapping(value = "/users/")
    public ResponseEntity<List<UserDto>> getUsersByCourse(@RequestParam("courseId") Long courseId) {
        List<User> users = courseService.users(courseId);
        return ResponseEntity.ok(userMapper.toListDto(users));
    }

}
