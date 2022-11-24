package com.example.formation.serviceImp;

import com.example.formation.model.User;
import com.example.formation.repository.UserRepository;
import com.example.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<User> user = userRepository.findById(id);
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
        User userFetched = userRepository.findById(user.getId()).get();
        if(Objects.isNull(userFetched)){
            return null;
        }
        userFetched.setName(user.getName());
        userFetched.setPassword(user.getPassword());
        userFetched.setRole(user.getRole() );
        return userRepository.save(userFetched);
    }

}
