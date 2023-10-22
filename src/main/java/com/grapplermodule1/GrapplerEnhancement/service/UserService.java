package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.cerebrus.config.UserDetailsConfig;
import com.grapplermodule1.GrapplerEnhancement.customexception.CustomExceptionHandler;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Role;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomExceptionHandler customExceptionHandler;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserDetailsConfig myUserDetails = new UserDetailsConfig(optionalUser.get());
            return myUserDetails;
        } else {
            throw new UsernameNotFoundException("Could not find user!!");
        }
    }

    /**
     * For Fetching All Users
     *
     * @return List<UsersDTO>
     */
    public List<UsersDTO> fetchAllUsers() {
        try {
            log.info("Fetch All Users Service Called");
            Optional<List<UsersDTO>> usersDTOS= userRepository.findAllUser();
            if(usersDTOS.isPresent()) {
                log.info("Fetch All Users Service Returning UserDTO");
                return usersDTOS.get();
            }
            else {
                log.error("Fetch All Users Service Call UserNotFoundException");
                throw new UserNotFoundException("Users Not found.");
            }
        }
        catch (Exception e) {
            log.info("Exception In Fetch All Users Exception {}", e.getMessage());
            throw  e;
        }
    }

    /**
     * For Adding A New User
     *
     * @return Users
     */
    public Users addUser(Users user) {
        try {
            log.info("Add New User Service Called");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Role role = new Role();
            role.setRole(user.getRole().getRole());

            user.setRole(role);
            role.setUser(user);

            Users newUser = userRepository.save(user);

            log.info("Add New User Service Returning New User");
            return newUser;
        } catch (Exception e) {
            log.error("Exception In Add User Service Exception {}", e.getMessage());
            throw e;
        }
    }

    /**
     * For Fetching User By ID
     *
     * @return Option<UsersDTO>
     */
    public UsersDTO fetchUserById(Long userId) {
        try {
            log.info("Fetch User By ID Service Called, User Id {}", userId);
            Optional<UsersDTO> usersDTO = userRepository.findUserDtoById(userId);
            if (usersDTO.isPresent()) {
                log.info("Fetch User By ID Service Returning UsersDTO");
                return usersDTO.get();
            } else {
                log.error("Fetch User By Id throws UserNotFoundException");
                throw new UserNotFoundException("User Not Found With ID : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception in Fetch User By Id Exception {}", e.getMessage());
            throw e;
        }
    }

    public Users updateUserDetails(Long userId, Users user) {
        try {
            log.info("Update User Details Service Called, User Id {}", userId);
            Optional<Users> existingOptionalUser = userRepository.findById(userId);

            if(existingOptionalUser.isPresent()){
                Users existingUser = existingOptionalUser.get();

                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setDesignation(user.getDesignation());
                existingUser.setReportingUser(user.getReportingUser());

                Users updatedUser = userRepository.save(existingUser);

                log.info("Update User Details Service Returning Updated User");
                return updatedUser;
            }
            else {
                log.error("Updated User Details throws UserNotFoundException");
                throw new UserNotFoundException("User Not Found With ID : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception in Update User Details Exception {}", e.getMessage());
            throw e;
        }
    }
}