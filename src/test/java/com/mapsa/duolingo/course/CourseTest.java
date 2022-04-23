package com.mapsa.duolingo.course;


import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CourseTest {

    @Mock
    CourseRepository repository;

    ICourseService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new CourseService(repository);
    }

    @Test
    public void getAll_shouldFindAllCourses() {
        //given
        Course course = getCourse();
        List<Course> courses = Collections.singletonList(course);
        //when
        when(repository.findAll()).thenReturn(courses);
        //then
        Assertions.assertEquals(course, service.getAll().get(0));
    }

    @Test
    public void getById_shouldGetCourse() {
        Course course = getCourse();
        when(repository.findById(ID)).thenReturn(Optional.of(course));
        Assertions.assertEquals(course, service.getById(ID));
    }

    @Test
    public void getById_whenNotFound_shouldThrowException() {
        when(repository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.getById(anyLong());
        });
    }

    @Test
    public void users_shouldReturnListUsers() {
        CourseUser cu = new CourseUser();
        User user = new User();
        Course course = getCourse();
        cu.setCourse(course);
        cu.setUser(user);
        List<CourseUser> cus = new ArrayList<>();
        cus.add(cu);
        List<User> users = new ArrayList<>();
        users.add(user);
        course.setUsers(cus);
//.getUsers().stream().map(CourseUser::getUser).collect(Collectors.toList())
        when(repository.findById(ID)).thenReturn(Optional.of(course));

        Assertions.assertEquals(course.getUsers().stream().map(CourseUser::getUser).collect(Collectors.toList()), service.users(ID));

    }

    @Test
    public void getLangById_shouldReturnLanguage() {
        Language language = new Language();
        language.setId(ID);

//        Course course = getCourse();
//        course.setLanguage(language);
        List<Course> courses = getListCourse();

        when(repository.findByLanguage(ID)).thenReturn(courses);

        Assertions.assertEquals(courses, service.getByLang(ID));
    }

    private Course getCourse() {
        Course course = new Course();
        course.setId(ID);
        return course;
    }

    private List<Course> getListCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(getCourse());
        return courses;
    }

}
