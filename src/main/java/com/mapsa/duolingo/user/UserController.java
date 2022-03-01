package com.mapsa.duolingo.user;


import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseMapper;
import com.mapsa.duolingo.courseUser.CourseUserService;
import com.mapsa.duolingo.security.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    IUserService userService;
    UserMapper mapper;
    CourseMapper courseMapper;
    UserLoginMapper loginMapper;
    CourseUserService courseUserService;
    UserDetail userDetail;


    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getById() {
        Long id = userDetail.getUserId();
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto newUser = mapper.toDto(userService.save(mapper.toEntity(userDto)));
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        return ResponseEntity.ok(userService.login(username, password));


    }

    @PostMapping(value = "/delete account")
    public ResponseEntity<HttpStatus> deleteAccount(@RequestParam Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/courses")
    public ResponseEntity<Course> getCoursesByUser(@RequestParam Long userId) {
        List<Course> courses = courseUserService.getCourseByUser(userId);
        return new ResponseEntity(courseMapper.toListDto(courses), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-course" , method = RequestMethod.POST)
    public ResponseEntity<Void> addCourse(@RequestParam Long courseId){
        userService.addCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
