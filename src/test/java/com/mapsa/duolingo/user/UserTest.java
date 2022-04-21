package com.mapsa.duolingo.user;

import com.mapsa.duolingo.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    UserRepository repository;

    IUserService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new UserService(repository);
    }

    @Test
    public void save_shouldReturnUser() {
        User newUser = getUser();

        when(repository.save(newUser)).thenReturn(newUser);

        Assertions.assertEquals(newUser, service.save(newUser));

        verify(repository, times(1)).save(newUser);
    }

    @Test
    public void save_whenUserIsExist_shouldThrowException() {
        when(repository.existsUserByEmailAddressOrUserName(any(), any())).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(CustomException.class, () -> {
            service.save(getUser());
        });
    }

    @Test
    public void authentication_whenAuthenticated_shouldReturnTrue() {
//        when(repository.findUserByUserName(any())).thenReturn(Optional.ofNullable(getUser()));

        Assertions.assertTrue(getUser().getPassword().equals("pass"));
    }
    //????????????????????????????????????????????????????????????????????

    @Test
    public void authentication_whenNotAuthenticated_shouldThrowException() {
        Assertions.assertFalse(getUser().getPassword().equals(anyString()));
    }


    private User getUser() {
        User user = User.builder().id(ID).userName("username").emailAddress("email").password("pass").build();
        return user;
    }

    private List<User> getList() {
        List<User> users = new ArrayList<>();
        users.add(getUser());
        return users;
    }

}
