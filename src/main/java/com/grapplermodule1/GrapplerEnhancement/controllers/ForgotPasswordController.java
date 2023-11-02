package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.customexception.CustomResponseMessage;
import com.grapplermodule1.GrapplerEnhancement.customexception.PasswordNotMatchException;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.entities.*;
import com.grapplermodule1.GrapplerEnhancement.repository.OtpRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import com.grapplermodule1.GrapplerEnhancement.service.EmailService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RequestMapping("/forgot-password")
@RestController
public class ForgotPasswordController {

    private Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    /**
     * For Sending Otp
     *
     * @return ResponseEntity
     */
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@Valid @RequestBody EmailDetails emailDetails) {
        try {
            log.info("ForgotPassword Controller Send OTP API Called {}", emailDetails.getEmail());
            Optional<Users> user = userRepository.findByEmail(emailDetails.getEmail());
            if (user.isPresent()) {
                Boolean status = emailService.sendSimpleMail(emailDetails.getEmail());
                log.info("OTP sent successfully to {}", emailDetails.getEmail());
                return new ResponseEntity<>(new CustomResponseMessage(status, "OTP sent successfully"), HttpStatus.OK);
            } else {
                log.error("User not found for email: {}", emailDetails.getEmail());
                return new ResponseEntity<>(new CustomResponseMessage(false, "User not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception in Send OTP API: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, "Failed to send OTP"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * For Verifying Otp
     *
     * @return ResponseEntity
     */
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@Valid @RequestBody OtpDetail otpDetail) {
        try {
            log.info("ForgotPassword Controller Verify OTP API Called");
            Boolean status = emailService.verifyOtp(otpDetail);
                log.info("OTP verified for email: {}", otpDetail.getOtp());
                return new ResponseEntity<>(new CustomResponseMessage(true, "OTP verified"), HttpStatus.OK);
        } catch (PasswordNotMatchException e) {
            log.error("PasswordNotMatchException in Verify OTP API: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Exception in Verify OTP API: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, "Failed to verify OTP"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * For Reset User Password
     *
     * @return ResponseEntity
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPassword resetPassword) {
        try {
            log.info("ForgotPassword Controller Reset Password API Called");
            Boolean status = emailService.resetUserPassword(resetPassword);
            log.info("Password Reset New Password Is ,{}", resetPassword.getNewPassword());
            return new ResponseEntity<>(new CustomResponseMessage(true, "Password Reset Successfully"), HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            log.error("UserNotFoundException in Reset Password Api: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
        catch (PasswordNotMatchException e) {
            log.error("PasswordNotMatchException in Reset Password Api: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Exception in Verify OTP API: {}", e.getMessage());
            return new ResponseEntity<>(new CustomResponseMessage(false, "Failed to verify OTP"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
