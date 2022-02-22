package com.mapsa.duolingo.language;

import com.mapsa.duolingo.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "language")
public class Language{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(name = "name")
    private String language;

}
