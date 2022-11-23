package com.example.formation.util;

import com.example.formation.model.User;
import com.example.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(
                new User(1,
                "user1",
                "1234",
                "USER"));
        userRepository.save(
                new User(2,
                        "admin",
                        "admin",
                        "ADMIN"));
    }
}
