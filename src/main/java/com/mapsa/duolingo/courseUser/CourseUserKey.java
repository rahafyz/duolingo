package com.mapsa.duolingo.courseUser;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUserKey implements Serializable {

    @Column(name = "user_id", insertable = false, updatable = false)
    Long userId;

    @Column(name = "course_id", insertable = false, updatable = false)
    Long courseId;

    @Override
    public boolean equals(Object o) {
        if(null == o) return false;
        if (this == o) return true;
//        if (!(o instanceof CourseUserKey that)) return false;
//        return userId.equals(that.userId) && courseId.equals(that.courseId);
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, courseId);
    }
}
