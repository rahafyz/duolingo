package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.courseUser.CourseUser;
import com.mapsa.duolingo.level.Level;
import com.mapsa.duolingo.level.LevelConverter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User  extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "username", unique = true)
    @NotNull
    private String userName;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email_address", unique = true)
    @NotNull
    private String emailAddress;

    @Convert(converter = LevelConverter.class)
    @Column(name = "user_level")
    private Level level= Level.BEGINNER;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CourseUser> courses;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", level=" + level +
                ", courses=" + courses +
                '}';
    }
}
