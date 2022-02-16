package com.mapsa.duolingo.user;

import com.mapsa.duolingo.exception.ConflictException;
import com.mapsa.duolingo.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User CreateNewUser(User user) {

        if(userRepository.existsUserByEmailAddressOrUserName(user.getEmailAddress(),user.getPassword()))
            throw new ConflictException("this user is already exist");
        return userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {

        if(!userRepository.existsById(userId))
            throw new NotFoundException("there is no user by this id!");

        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        if(!userRepository.existsById(userId))
            throw new NotFoundException("there is no user by this id!");

        userRepository.deleteById(userId);
    }

    @Override
    public void changeLevel(Long userId) {

    }

    @Override
    public User login(String username, String password) {
        if(userRepository.findUserByUserNameAndPassword(username,password).isEmpty())
            throw new RuntimeException();
        return userRepository.findUserByUserNameAndPassword(username,password).get();
    }
}
