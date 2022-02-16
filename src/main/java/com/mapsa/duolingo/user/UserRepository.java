package com.mapsa.duolingo.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    boolean existsUserByEmailAddressOrUserName(String emailAddress,String username);
    Optional<User> findUserByUserNameAndPassword(String username, String password);
}