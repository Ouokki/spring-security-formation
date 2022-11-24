package com.example.formation.service;

import com.example.formation.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUser(Integer id);
    User saveUser(User user);

    void deleteUserById(Integer id);

    User modifyUser(User user);
}
