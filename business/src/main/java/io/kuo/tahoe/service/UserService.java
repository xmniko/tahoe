package io.kuo.tahoe.service;

import io.kuo.tahoe.entity.User;
import io.kuo.support.jersey.exception.ResourceNotFoundException;
import io.kuo.tahoe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nikog on 2/4/2015.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User(id:%s) not found.", id));
        }
        return user;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User(email:%s) not found.", email));
        }
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long userId) {
        userRepository.delete(userId);
    }
}
