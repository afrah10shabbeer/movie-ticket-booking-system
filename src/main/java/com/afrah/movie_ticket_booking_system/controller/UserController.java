package com.afrah.movie_ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afrah.movie_ticket_booking_system.DataTransferObjects.CreateUserRequest;
import com.afrah.movie_ticket_booking_system.entity.User;
import com.afrah.movie_ticket_booking_system.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Create a user", description = "Creates a new user.")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {

        User user = service.createUser(
                request.getName(),
                request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Get all users", description = "Retrieves all registered users.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Operation(summary = "Get user by ID", description = "Retrieves a user using its unique ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }

    @Operation(summary = "Delete a user", description = "Deletes a user using its unique ID.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String userId) {
        service.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}