package bearcation.service;

import bearcation.model.User;
import bearcation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public List<User> create(){
        User user = new User("123456", "John");
        List<User> users = new ArrayList<User>();
        List<User> savedUsers = new ArrayList<User>();

        users.add(user);
        Iterable<User> itrStudents=userRepository.saveAll(users);
        itrStudents.forEach(savedUsers::add);
        return savedUsers;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User userById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public User retrieveUser(long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;

    }
}
