package com.mapsa.duolingo.language;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ELanguage language;

}
