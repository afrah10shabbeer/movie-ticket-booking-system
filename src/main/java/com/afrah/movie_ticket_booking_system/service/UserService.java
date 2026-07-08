package com.afrah.movie_ticket_booking_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.User;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;

import com.afrah.movie_ticket_booking_system.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {

        logger.info("Creating user with email '{}'.", email);

        User user = new User(name, email);

        User savedUser = userRepository.save(user);

        logger.info("User '{}' created successfully with ID '{}'.",
                savedUser.getName(), savedUser.getUserId());

        return savedUser;
    }

    public List<User> getAllUsers() {

        logger.info("Fetching all users.");

        return userRepository.findAll();
    }

    public User getUserById(String userId) {

        logger.info("Fetching user with ID '{}'.", userId);

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            logger.info("User '{}' fetched successfully.", user.get().getName());
            return user.get();
        }

        logger.warn("User not found with ID '{}'.", userId);

        throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    public void deleteUserById(String userId) {

        logger.info("Deleting user with ID '{}'.", userId);

        User user = getUserById(userId);

        userRepository.delete(user);

        logger.info("User '{}' deleted successfully.", user.getName());
    }
}
