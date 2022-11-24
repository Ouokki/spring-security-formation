package com.example.formation.serviceImp;

import com.example.formation.exceptions.UserNotFoundException;
import com.example.formation.model.User;
import com.example.formation.repository.UserRepository;
import com.example.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Integer id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found")));
        return user.get();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User modifyUser(User user) {
        Optional<User> userFetched = Optional.ofNullable(userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + user.getId() + " not found")));
        userFetched.get().setName(user.getName());
        userFetched.get().setPassword(user.getPassword());
        userFetched.get().setRole(user.getRole() );
        return userRepository.save(userFetched.get());
    }

}
