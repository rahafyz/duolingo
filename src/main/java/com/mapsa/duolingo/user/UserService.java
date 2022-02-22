package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.exception.ConflictException;
import com.mapsa.duolingo.exception.CustomException;
import com.mapsa.duolingo.exception.NotFoundException;
import com.mapsa.duolingo.security.JwtBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserService extends GenericService<User, Long> implements IUserService {

    private UserRepository userRepository;
    private JwtBuilder jwtBuilder;

    public UserService(GenericRepository<User, Long> repository, UserRepository userRepository, JwtBuilder jwtBuilder) {
        super(repository);
        this.userRepository = userRepository;
        this.jwtBuilder = jwtBuilder;
    }

    @Override
    public User save(User user) {

        if (userRepository.existsUserByEmailAddressOrUserName(user.getEmailAddress(), user.getPassword()))
            throw new ConflictException("this user is already exist");
        return userRepository.save(user);
    }

    //jwt
    @Override
    public String login(String username, String password) {
        try {
            if (authentication(username, password)) {
                return jwtBuilder.createToken(username);
            }
        } catch (Exception exception) {
            throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return null;
    }

    private boolean authentication(String username, String password) {
        User currentUser = userRepository.findUserByUserName(username).orElseThrow(
                () -> new NotFoundException("user doesn't exist.")
        );
        return currentUser.getPassword().equals(password);
    }
}
