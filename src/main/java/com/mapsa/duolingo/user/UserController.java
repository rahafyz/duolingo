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


    @RequestMapping(value = "/GET /user/", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getById() {
        Long id = userDetail.getUserId();
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }


    @PostMapping(value = "/POST /user")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto newUser = mapper.toDto(userService.save(mapper.toEntity(userDto)));
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        return ResponseEntity.ok(userService.login(username, password));


    }

    @RequestMapping(value = "/POST /delete/", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteAccount() {
        userService.delete(userDetail.getUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/GET /courses/")
    public ResponseEntity<Course> getCoursesByUser() {
        List<Course> courses = courseUserService.getCourseByUser(userDetail.getUserId());
        return new ResponseEntity(courseMapper.toListDto(courses), HttpStatus.OK);
    }

    @RequestMapping(value = "/POST /course" , method = RequestMethod.POST)
    public ResponseEntity<Void> addCourse(@RequestParam Long courseId){
        userService.addCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
