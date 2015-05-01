package io.kuo.tahoe.service;

import io.kuo.tahoe.entity.User;
import io.kuo.support.jersey.exception.ResourceNotFoundException;
import io.kuo.tahoe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nikog on 2/4/2015.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User loadById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User(id:%s) not found.", id));
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User(email:%s) not found.", email));
        }
        return user;
    }
}
