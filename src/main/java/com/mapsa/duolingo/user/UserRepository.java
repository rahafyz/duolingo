package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.GenericRepository;
import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.courseUser.CourseUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User,Long> {
    boolean existsUserByEmailAddressOrUserName(String emailAddress,String username);
    Optional<User> findUserByUserName(String username);

    @Query(value = " select * from duolingo.user u inner join duolingo.course_user cu on cu.user_id=u.user_id Inner join duolingo.course c on cu.course_id=c.course_id where c.name= :courseName",nativeQuery = true)
    @Cacheable(value = "user" , key = "#p0")
    List<User> findByCourse_name(@Param("courseName") String courseName);

    @Override
    @Cacheable(value = "users")
    Iterable<User> findAll(Sort sort);

    @Override
    @Cacheable(value = "users", key = "#p0")
    Optional<User> findById(Long aLong);

    @Override
    @CacheEvict(value = "users", allEntries = true)
    <S extends User> S save(S entity);

    @Override
    @CacheEvict(value = "users", key = "#p0")
    void deleteById(Long aLong);
}