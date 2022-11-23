package com.example.formation.controller;


import com.example.formation.model.User;
import com.example.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/Users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/findAllUser")
    private List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/findUser/{id}")
    private User findUser(@RequestParam("id") Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @PostMapping("/")
    private User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
