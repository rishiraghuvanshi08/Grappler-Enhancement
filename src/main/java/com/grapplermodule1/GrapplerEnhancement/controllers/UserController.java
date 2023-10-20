package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.customxception.CustomResponse;
import com.grapplermodule1.GrapplerEnhancement.customxception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dto.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    /**
     * For Getting List Of Users
     *
     * @return ResponseEntity<List < UsersDTO>>
     */
    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        String debugUuid = UUID.randomUUID().toString();
        Optional<List<UsersDTO>> usersList = null;
        try {
            log.info("inside getAllUsers,UUID {}");
            usersList = userService.fetchAllUsers();
            if (usersList.isPresent()) {
                log.info("Get All Users API Called, UUID {}", debugUuid);
                return new ResponseEntity<>(usersList.get(), HttpStatus.OK);
            } else {
                log.error("UUID {} Get All Users API Called Users Not Found", debugUuid);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (UserNotFoundException e) {
            log.error("UUID {} UserNotFoundException In Get All Users API Exception {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("UUID {} Exception In Get All Users API Exception {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * For Create New User
     *
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        String debugUuid = UUID.randomUUID().toString();
        try {
            log.info("inside createUser,UUID {}");
            Boolean flag = userService.addUser(user);
            if (flag) {
                log.info("Get Create User API Called, UUID {}", debugUuid);
                return new ResponseEntity<>("User Created Successfully", HttpStatus.OK);
            } else {
                log.error("UUID {} User Not Created", debugUuid);
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            log.error("UUID {} Exception In Create User API Exception {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * For getting User By I'd
     *
     * @return ResponseEntity<Users>
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        String debugUuid = UUID.randomUUID().toString();
        try {
            log.info("Inside Get User By Id,UUID {} ", userId);
            Optional<UsersDTO> user = userService.fetchUserById(userId);

            log.info("Get User By Id API Called, UUID {}", debugUuid);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        }
        catch (UserNotFoundException e) {
            log.error("UUID {} UserNotFoundException In Get All Users API Exception {}", debugUuid, e);
            return new ResponseEntity<>(new CustomResponse<>(HttpStatus.NOT_FOUND,e.getMessage(),null), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            log.error("UUID {} Exception In Get User By Id API Exception {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
