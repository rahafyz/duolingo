package com.mapsa.duolingo.user;


import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.course.CourseMapper;
import com.mapsa.duolingo.exam.ExamService;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.security.AuthenticationService;
import com.mapsa.duolingo.security.UserDetail;
import com.mapsa.duolingo.test.TestService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private IUserService userService;
    private UserMapper mapper;
    private CourseMapper courseMapper;
    private UserDetail userDetail;
    private ExamService examService;
    private TestService testService;
    private AuthenticationService authenticationService;


    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getById() {
        Long id = userDetail.getUserId();
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }


    @PostMapping(value = "/user")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto newUser = mapper.toDto(userService.register(mapper.toEntity(userDto)));
        return new ResponseEntity(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam String username, @RequestParam String password) {
        if (!userService.authentication(username, password))
            throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userService.findByUserName(username);
        authenticationService.sendVerificationCode(user);
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @PostMapping("/verification/{verificationCode}")
    public ResponseEntity<String> verification(@RequestParam String username, @PathVariable String verificationCode) {
        return ResponseEntity.ok(authenticationService.verification(username, verificationCode));
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteAccount() {
        userService.delete(userDetail.getUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/courses/")
    public ResponseEntity<List<Course>> getCoursesByUser() {
        List<Course> courses = userService.getUserCourses(userService.getById(userDetail.getUserId()));
        return new ResponseEntity(courseMapper.toListDto(courses), HttpStatus.OK);
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public ResponseEntity<Void> addCourse(@RequestParam Long courseId) {
        userService.addCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Resource> downloadFile(@RequestParam Long examId, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = examService.loadFileExam(examId);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<String> uploadFile(@RequestParam("examId") Long examId, @RequestParam MultipartFile file) {
        String fileName = testService.storeFile(userDetail.getUserId(), examId, file);

        ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        testService.save(userDetail.getUserId(), examId);


        return ResponseEntity.ok("http://localhost:9090/user/level");
    }

    @PutMapping(value = "/level")
    public ResponseEntity<UserDto> changeLevel() {

        return ResponseEntity.ok(mapper.toDto(userService.changeLevel(userDetail.getUserId())));
    }

    @GetMapping(value = "/course/")
    public ResponseEntity<List<UserDto>> search(@RequestParam String courseName) {
        List<User> users = userService.findByCourseName(courseName);
        return ResponseEntity.ok(mapper.toListDto(users));
    }
}
