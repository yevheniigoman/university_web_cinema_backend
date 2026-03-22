package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.User;
import com.iasaweb.cinema.repository.UserRepository;
import com.iasaweb.cinema.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }
}
