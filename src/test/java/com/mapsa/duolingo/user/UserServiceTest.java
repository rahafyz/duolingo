package com.mapsa.duolingo.user;

import com.mapsa.duolingo.course.ICourseService;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.courseUser.CourseUserKey;
import com.mapsa.duolingo.courseUser.ICourseUserService;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.security.JwtBuilder;
import com.mapsa.duolingo.security.UserDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    UserRepository repository;

    @Spy
    JwtBuilder jwtBuilder;

    @Spy
    ICourseUserService courseUserService;

    @Spy
    UserDetail userDetail;

    IUserService service;

    @Mock
    ICourseService courseService;


    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new UserService(repository, jwtBuilder, courseUserService, userDetail);
        userDetail.setUserId(ID);
        MockitoAnnotations.openMocks(courseService);
    }

    @Test
    public void save_shouldReturnUser() {
        User newUser = getUser();

        when(repository.save(newUser)).thenReturn(newUser);

        Assertions.assertEquals(newUser, service.save(newUser));
        Assertions.assertNotNull(newUser.getId());

        verify(repository, times(1)).save(newUser);
    }

    @Test
    public void save_whenUserExist_shouldThrowException() {
        when(repository.existsUserByEmailAddressOrUserName(any(), any())).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(CustomException.class, () -> {
            service.save(getUser());
        });
    }

    @Test
    public void getById_shouldReturnUser() {

        when(repository.findById(ID)).thenReturn(Optional.of(getUser()));

        Assertions.assertEquals(getUser(), service.getById(ID));
        Assertions.assertNotNull(getUser().getId());
        Assertions.assertEquals(getUser().getUserName(), service.getById(ID).getUserName());
        Assertions.assertEquals(getUser().getPassword(), service.getById(ID).getPassword());
        Assertions.assertEquals(getUser().getLastName(), service.getById(ID).getLastName());
        Assertions.assertEquals(getUser().getEmailAddress(), service.getById(ID).getEmailAddress());
    }

    @Test
    public void getById_whenNotFound_shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.getById(ID);
        });
    }

    @Test
    public void getAll_WhenNoUserExist_shouldReturnNull() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.getAll());
    }

    @Test
    public void getAll_shouldReturnListUsers() {
        when(repository.findAll()).thenReturn(getList());

        Assertions.assertEquals(getList(), service.getAll());
        /*getList().stream().forEach(user -> {
            Assertions.assertEquals(user,service.getAll().get());
        });*/

        Assertions.assertArrayEquals(getList().toArray(), service.getAll().toArray());

        Assertions.assertEquals(getList().size(), service.getAll().size());
    }


    /*@Test
    public void login_whenAuthenticated_shouldReturnToken() {

        String Token = jwtBuilder.generateToken(getUser());

        when(repository.findUserByUserName("username")).thenReturn(Optional.of(getUser()));

        Assertions.assertEquals(jwtBuilder.getUsernameFromToken(Token), jwtBuilder.getUsernameFromToken(service.login(getUser().getUserName(), getUser().getPassword())));

    }

    @Test
    public void login_whenUserNotFound_shouldThrowException() {
        when(repository.findUserByUserName(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.login("anyString()", "anyString()");
        });
    }

    @Test
    public void login_whenNotAuthenticated_shouldThrowException() {
        when(repository.findUserByUserName("username")).thenReturn(Optional.of(getUser()));

        Assertions.assertThrows(CustomException.class, () -> {
            service.login(getUser().getUserName(), "any");
        });
    }*/

    @Test
    public void addCourse_shouldAddToCourses() {
        CourseUserKey cuKey = CourseUserKey.builder().courseId(ID).userId(userDetail.getUserId()).build();

        CourseUser cu = CourseUser.builder().user(getUser()).build();

        when(courseUserService.save(cuKey)).thenReturn(cu);

        service.addCourse(ID);

        Mockito.verify(courseUserService, times(1)).save(cuKey);

    }

    @Test
    public void addCourse_whenCourseNotExist_shouldThrowException() {
        CourseUserKey cuKey = CourseUserKey.builder().courseId(ID).userId(userDetail.getUserId()).build();

        when(courseUserService.save(cuKey)).thenThrow(CustomException.class);

        Assertions.assertThrows(CustomException.class, () -> {
            service.addCourse(ID);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/change_level.csv", numLinesToSkip = 1)
    public void ChangeState_shouldWork(Level first, Level second) {
        User user = User.builder().id(ID).level(first).build();
        when(repository.findById(ID)).thenReturn(Optional.of(user));

        Assertions.assertEquals(service.changeLevel(ID).getLevel(), second);

//        Mockito.verify(repository.save(user),times(1));

    }


    private User getUser() {
        User user = User.builder().id(ID).firstName("firstName").lastName("lastName")
                .userName("username").emailAddress("email").password("pass")
                .level(Level.BEGINNER).build();
        return user;
    }

    private List<User> getList() {
        List<User> users = new ArrayList<>();
        users.add(getUser());
        User user = User.builder().id(3l).userName("un").firstName("fn").lastName("ln").password("ps").emailAddress("em").build();
        users.add(user);
        return users;
    }

}
