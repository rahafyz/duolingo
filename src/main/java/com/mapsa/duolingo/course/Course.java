package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.level.LevelConverter;
import com.mapsa.duolingo.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_language")
    private Language language;

    @Convert(converter = LevelConverter.class)
    private Level level;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private List<CourseUser> users;




}
