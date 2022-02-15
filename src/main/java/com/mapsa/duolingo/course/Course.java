package com.mapsa.duolingo.course;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.language.Language;
import com.mapsa.duolingo.level.Level;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "course")
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

    @ManyToOne
    @JoinColumn(name = "course_level")
    private Level level;




}
