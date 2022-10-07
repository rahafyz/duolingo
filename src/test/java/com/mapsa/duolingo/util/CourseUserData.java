package com.mapsa.duolingo.util;

import com.mapsa.duolingo.courseUser.CourseUserKey;

public class CourseUserData {
    public static CourseUserKey courseUser(Long userId,Long courseId) {
        CourseUserKey cu = new CourseUserKey();
        cu.setUserId(userId);
        cu.setCourseId(courseId);
        return cu;
    }
}
