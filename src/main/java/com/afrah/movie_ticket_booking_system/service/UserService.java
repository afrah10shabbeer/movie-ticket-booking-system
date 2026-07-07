package com.afrah.movie_ticket_booking_system.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.afrah.movie_ticket_booking_system.entity.User;
import com.afrah.movie_ticket_booking_system.exception.ResourceNotFoundException;

@Service
public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Map<String, User> getAllUsers() {
        return users;
    }

    public User getUserById(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }
        throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    public void deleteUserById(String userId) {
        User user = users.remove(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }
}
