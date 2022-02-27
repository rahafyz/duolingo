package com.mapsa.duolingo.courseUser;

import com.mapsa.duolingo.course.CourseDto;
import com.mapsa.duolingo.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseUserDto implements Serializable {
    private UserDto user;
    private CourseDto course;
}
