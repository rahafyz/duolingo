package com.mapsa.duolingo.courseUser;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseUserKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "course_id")
    Long courseId;


}
