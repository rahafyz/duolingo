package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.exception.ConflictException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, Long> implements IUserService {

    private UserRepository userRepository;

    public UserService(GenericRepository<User, Long> repository) {
        super(repository);
        this.userRepository = (UserRepository) repository;
    }


    @Override
    public User save(User user) {

        if (userRepository.existsUserByEmailAddressOrUserName(user.getEmailAddress(), user.getPassword()))
            throw new ConflictException("this user is already exist");
        return userRepository.save(user);
    }

//jwt

    @Override
    public User login(String username, String password) {
        if (userRepository.findUserByUserNameAndPassword(username, password).isEmpty())
            throw new RuntimeException();
        return userRepository.findUserByUserNameAndPassword(username, password).get();
    }
}
