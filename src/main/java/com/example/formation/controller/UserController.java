package com.example.formation.controller;


import com.example.formation.model.User;
import com.example.formation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("API pour les opérations CRUD sur les users.")
@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("Api for fetching all users")
    @GetMapping("/findAllUser")
    private ResponseEntity<List<User>> findAllUsers(){
        System.out.println("7zgui");
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAllUsers());
    }

    @ApiOperation("Api to fetch a user by its Id")
    @GetMapping("/{id}")
    private ResponseEntity<User> findUser(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUser(id));
    }

    @ApiOperation("Api to create a user")
    @PostMapping("/")
    private ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @ApiOperation("Api to delete a user by its id")
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @ApiOperation("Api to modify a user")
    @PutMapping("/")
    private ResponseEntity<User> modifyUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyUser(user));
    }
}
