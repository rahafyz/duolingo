package com.mapsa.duolingo.courseUser;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CourseUserKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "course_id")
    Long courseId;

    @Override
    public boolean equals(Object o) {
        if(null == o) return false;
        if (this == o) return true;
        if (!(o instanceof CourseUserKey that)) return false;
        return userId.equals(that.userId) && courseId.equals(that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
