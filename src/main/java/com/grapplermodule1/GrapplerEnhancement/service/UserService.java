package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.cerebrus.config.UserDetailsConfig;
import com.grapplermodule1.GrapplerEnhancement.controllers.UserController;
import com.grapplermodule1.GrapplerEnhancement.customxception.CustomExceptionHandler;
import com.grapplermodule1.GrapplerEnhancement.customxception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dto.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<List<UsersDTO>> fetchAllUsers() {
        try {
            Optional<List<UsersDTO>> usersDTOS= userRepository.findAllUser();
            if(usersDTOS.isPresent())
            {
                return  usersDTOS;
            }
            else {
                  throw new UserNotFoundException("User Not found");
            }
        }
        catch (Exception e) {
            throw  e;
        }
    }

    public Boolean addUser(Users user) {
        try {
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             Users newUser = userRepository.save(user);
            if (newUser != null) {
                 return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public Optional<UsersDTO> fetchUserById(Long userId)  {
        try {
            Optional<UsersDTO> usersDTO = userRepository.findUserDtoById(userId);
            if (usersDTO.isPresent()) {
                return usersDTO;
            } else {
                 log.info("Inside else block");
                 throw new UserNotFoundException("User Not Found With id : " + userId);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
