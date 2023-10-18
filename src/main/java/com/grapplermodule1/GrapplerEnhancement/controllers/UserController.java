package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    /**
     * For Getting List Of Users
     *
     * @return ResponseEntity<List<Users>>
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Users>> getAllUsers() {
        return null;
    }

    /**
     * For Create New User
     *
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody Users users) {
        return null;
    }

    /**
     * For getting User By Id
     *
     * @return ResponseEntity<Users>
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable("userId") Long userId) {
        return null;
    }

    /**
     * For Update User By Id
     *
     * @return ResponseEntity
     */
    @PutMapping("/{userId}")
    public ResponseEntity updateUserById(@PathVariable("userId") Long userId,
                                         @RequestBody Users user) {
        return null;
    }

    /**
     * For Delete User
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId) {
        return null;
    }
}
