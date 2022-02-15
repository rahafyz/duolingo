package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "course_user")
public class CourseUser {

    @EmbeddedId
    CourseUserKey id;

    @ManyToOne
    @MapsId("userId")
            @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("courseId")
            @JoinColumn(name = "course_id")
    Course course;

}
