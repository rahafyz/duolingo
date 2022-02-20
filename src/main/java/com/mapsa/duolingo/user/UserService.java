package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.common.GenericService;
import com.mapsa.duolingo.exception.ConflictException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User,Long> implements IUserService {

    private UserRepository userRepository;

    public UserService(GenericRepository<User, Long> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (userRepository.existsUserByEmailAddressOrUserName(user.getEmailAddress(), user.getPassword()))
            throw new ConflictException("this user is already exist");
        return userRepository.save(user);
    }

//jwt
    @Override
    public User login(User user) {
        if (userRepository.findUserByUserNameAndPassword(user.getUserName(), user.getPassword()).isEmpty())
            throw new RuntimeException();
        return userRepository.findUserByUserNameAndPassword(user.getUserName(), user.getPassword()).get();
    }
}
