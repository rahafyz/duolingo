package com.mapsa.duolingo.user;

import com.mapsa.duolingo.common.BaseEntity;
import com.mapsa.duolingo.level.Level;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity {


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

    @ManyToOne
    @JoinColumn(name = "user_level")
    private Level level;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
