package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User,Long> {
    boolean existsUserByEmailAddressOrUserName(String emailAddress,String username);
    Optional<User> findUserByUserNameAndPassword(String username, String password);
}