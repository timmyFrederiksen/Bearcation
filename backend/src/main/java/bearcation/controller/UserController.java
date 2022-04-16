package bearcation.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import bearcation.model.User;
import bearcation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(){
        User user = new User("123456", "John");

        List<User> userList  =  userservice.create();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(userList.get(0).getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{userId}")
    public User retrieveStudent(@PathVariable long userId) {
        return userservice.retrieveUser(userId);
    }


}
