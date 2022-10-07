package com.mapsa.duolingo.course;


import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.courseUser.CourseUserRepository;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.level.Level;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    CourseRepository repository;

    @Mock
    CourseUserRepository courseUserRepository;

    ICourseService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new CourseService(repository,courseUserRepository);
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
    }//itarate

    @Test
    public void save_shouldReturnCourse() {
        Course course = getCourse();

        when(repository.save(course)).thenReturn(course);

        Assertions.assertEquals(course, service.save(course));
        Assertions.assertNotNull(service.save(course).getId());

        verify(repository, times(2)).save(course);
    }


    @Test
    public void getById_shouldGetCourse() {
        Course course = getCourse();

        when(repository.findById(ID)).thenReturn(Optional.of(course));

        Assertions.assertEquals(course, service.getById(ID));
        Assertions.assertNotNull(service.getById(ID));
        Assertions.assertEquals(course.getId(), service.getById(ID).getId());
        Assertions.assertEquals(course.getName(), service.getById(ID).getName());
        Assertions.assertEquals(course.getLanguage(), service.getById(ID).getLanguage());
    }

    @Test
    public void getById_whenNotFound_shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.getById(ID);
        });
    }

    @Test
    public void getAll_shouldReturnCourseList() {
        when(repository.findAll()).thenReturn(getListCourse());

        getListCourse().forEach(course -> {
            Assertions.assertTrue(service.getAll().contains(course));
        });

        Assertions.assertArrayEquals(getListCourse().toArray(),
                service.getAll().toArray());

        Assertions.assertEquals(getListCourse().size(), service.getAll().size());
    }


    @Test
    public void getAll_WhenNoCourseExist_shouldReturnNull() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.getAll());
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
        when(courseUserRepository.findByCourse_Id(ID)).thenReturn(cus);

        Assertions.assertEquals(cus.stream().map(CourseUser::getUser).collect(Collectors.toList()), service.users(ID));
    }

    @Test
    public void getLangById_shouldReturnLanguage() {
        Language language = new Language();
        language.setId(ID);

        List<Course> courses = getListCourse();

        when(repository.getByLanguage_Id(ID)).thenReturn(courses);

        Assertions.assertEquals(courses, service.getByLang(ID));
    }

    private Course getCourse() {
        Course course = Course.builder().id(ID).name("EnglishCourse").level(Level.ADVANCED).build();
        return course;
    }

    private List<Course> getListCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(getCourse());
        courses.add(Course.builder().id(3l).name("name").build());
        return courses;
    }

}
