package com.mapsa.duolingo.course;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapsa.duolingo.courseUser.CourseUserRepository;
import com.mapsa.duolingo.courseUser.CourseUserService;
import com.mapsa.duolingo.exception.GlobalExceptionHandler;
import com.mapsa.duolingo.language.LanguageRepository;
import com.mapsa.duolingo.user.UserMapper;
import com.mapsa.duolingo.user.UserRepository;
import com.mapsa.duolingo.util.LanguageData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mapsa.duolingo.util.CourseData.course;
import static com.mapsa.duolingo.util.CourseData.courseDto;
import static com.mapsa.duolingo.util.CourseUserData.courseUser;
import static com.mapsa.duolingo.util.LanguageData.language;
import static com.mapsa.duolingo.util.UserData.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {


    @Autowired
    CourseService service;

    @Autowired
    CourseRepository repository;

    @Autowired
    private CourseMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    GlobalExceptionHandler exceptionHandler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseUserService courseUserService;

    @Autowired
    CourseUserRepository courseUserRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CourseController controller = new CourseController(service, mapper, userMapper);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(jacksonMessageConverter)
                .setControllerAdvice(exceptionHandler)
                .build();
        objectMapper = new ObjectMapper();
    }

    /*@BeforeEach
    void init() {
        languageRepository.save(LanguageData.language(0));

        repository.save(course(0));

        userRepository.save(user(0));

        courseUserService.save(courseUser(1L, 1L));
    }

    @AfterEach
    @Transactional
    void delete() {
        courseUserRepository.deleteAll();

        repository.deleteAll();

        languageRepository.deleteAll();

        userRepository.deleteAll();
    }*/

    @Test
    void save() throws Exception {
        languageRepository.save(LanguageData.language(0));

        repository.save(course(0));

        long countBeforeSave = repository.count(); //0

        CourseDto newCourseDto = courseDto(0);

        mockMvc.perform(post("/course").contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(newCourseDto)))
                .andExpect(status().isOk());
        long countAfterSave = repository.count(); //1

        List<Course> courses = repository.findAll();
        Course newCourse = courses.get(courses.size() - 1);

        assertEquals(countAfterSave, countBeforeSave + 1);

        assertNotNull(newCourse.getId());
        assertEquals(newCourseDto.getName(), newCourse.getName());
        assertEquals(newCourseDto.getLanguage(), newCourse.getLanguage());
        assertEquals(newCourseDto.getLevel(), newCourse.getLevel());

    }

    @Test
    void getByLanguage() throws Exception {
        languageRepository.save(LanguageData.language(0));

        repository.save(course(0));

        this.mockMvc.perform(get("/course")
                        .param("languageId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value(course(0).getName()))
                .andExpect(jsonPath("$[0].language.language").value(course(0).getLanguage().getLanguage()))
                .andExpect(jsonPath("$[0].level").value(course(0).getLevel().name()));

    }

    @Test
    void getUsersByCourse() throws Exception {
        languageRepository.save(LanguageData.language(0));

        repository.save(course(0));

        userRepository.save(user(0));

        courseUserService.save(courseUser(1L, 1L));

        this.mockMvc.perform(get("/course/users/")
                        .param("courseId", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value(user(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(user(0).getLastName()))
                .andExpect(jsonPath("$[0].userName").value(user(0).getUserName()))
                .andExpect(jsonPath("$[0].emailAddress").value(user(0).getEmailAddress()))
                .andExpect(jsonPath("$[0].level").value(user(0).getLevel().name()));

    }

    @Test
    void getAllCourses() throws Exception {
        languageRepository.save(language(0));
        repository.save(course(0));
        languageRepository.save(language(1));
        repository.save(course(1));
        this.mockMvc.perform(get("/course/all"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value(course(0).getName()))
                .andExpect(jsonPath("$[0].language").value(course(0).getLanguage()))
                .andExpect(jsonPath("$[0].level").value(course(0).getLevel().name()))
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].name").value(course(1).getName()))
                .andExpect(jsonPath("$[1].language").value(course(1).getLanguage()))
                .andExpect(jsonPath("$[1].level").value(course(1).getLevel().name()));
    }
}
