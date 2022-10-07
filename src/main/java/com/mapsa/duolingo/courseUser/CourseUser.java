package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.Course;
import com.mapsa.duolingo.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_user")
public class CourseUser {

    @EmbeddedId
    CourseUserKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
            @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("courseId")
            @JoinColumn(name = "course_id")
    Course course;

}
