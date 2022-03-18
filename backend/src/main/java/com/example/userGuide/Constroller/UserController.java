package com.example.userGuide.Constroller;
// check connection

import com.example.userGuide.Service.UserService;
import com.example.userGuide.model.User;
import com.example.userGuide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User UserById(@PathVariable("id") Long id) {
        return userService.userById(id);
    }

    @PostMapping("users")
    public User createEmployee(@RequestBody User employee) {
        return userService.createUser(employee);
    }
    /*
    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

     */
}


