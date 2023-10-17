package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Permission;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /*
     *
     *
     * */
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() {
        return null;
    }

    /**
     * For
     *
     * @return
     */
    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody Users users) {
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable("userId") Long userId) {
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUserById(@PathVariable("userId") Long userId,
                                         @RequestBody Users user) {
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId) {
        return null;
    }

}
