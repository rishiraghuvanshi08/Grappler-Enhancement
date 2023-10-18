package com.grapplermodule1.GrapplerEnhancement.controllers;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * For Getting List Of Users
     *
     * @return ResponseEntity<List<Users>>
     */
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() {
        log.info("inside getAllUsers");
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
