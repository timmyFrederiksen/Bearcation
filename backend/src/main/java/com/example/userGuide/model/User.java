package com.example.userGuide.model;

import javax.persistence.*;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
public class User {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    public User() {

    }

    public User(String password, String username) {
        super();
        this.password = passwordEncoder.encode(password);
        this.username = username;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }
    public String getUsername() {
        return username;
    }
    public void setLastName(String username) {
        this.username = username;
    }
}
