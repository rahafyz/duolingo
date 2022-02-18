package com.mapsa.duolingo.level;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Long id;

    @Column(name = "level")
    private String level;

}
