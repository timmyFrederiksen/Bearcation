package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserTests {

    //transient PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

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
        this.password = password;
        //this.password = passwordEncoder.encode(password);
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
        //this.password = passwordEncoder.encode(password);
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setLastName(String username) {
        this.username = username;
    }
}
