package com.mapsa.duolingo.test;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.exam.Exam;
import com.mapsa.duolingo.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "test")
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "mark")
    private Double mark;

}
