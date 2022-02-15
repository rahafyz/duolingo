package com.mapsa.duolingo.exam;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.language.Language;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "exam")
public class Exam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(name = "file_location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "emax_language")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "exam_level")
    private Level level;


}
