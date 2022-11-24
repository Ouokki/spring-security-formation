package com.example.formation.controller;


import com.example.formation.model.User;
import com.example.formation.repository.UserRepository;
import com.example.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAllUser")
    private ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> findUser(@RequestParam("id") Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUser(id));
    }

    @PostMapping("/")
    private ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUserById(@RequestParam Integer id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @PutMapping("/")
    private ResponseEntity<User> modifyUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyUser(user));
    }
}
