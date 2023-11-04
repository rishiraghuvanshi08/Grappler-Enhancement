package com.grapplermodule1.GrapplerEnhancement.service;
import com.grapplermodule1.GrapplerEnhancement.customexception.PasswordNotMatchException;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.entities.*;
import com.grapplermodule1.GrapplerEnhancement.repository.OtpRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;
@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}") private String sender;

    /**
     * For Sending Otp
     *
     * @return Boolean
     */
    public Boolean sendSimpleMail(String email)
    {
       log.info("Send Email Service called");
       try{
           Random random = new Random();
           int otp = 100000 + random.nextInt(900000);
           otpRepository.deleteAll();
           OtpStore otpStore=new OtpStore();
           otpStore.setOtp(otp);
           otpStore.setEmail(email);
           otpRepository.save(otpStore);
           SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
           simpleMailMessage.setFrom(sender);
           simpleMailMessage.setTo(email);
           simpleMailMessage.setText("Your Otp Is:"+ otp);
           simpleMailMessage.setSubject("For Forgot Password");
           javaMailSender.send(simpleMailMessage);
           log.info("Send Email Service Returning True ");
           return  true;
       }
       catch (Exception e)
       {
           log.error("Exception In Send Email Service Exception {}", e.getMessage());
           throw e;
       }
    }

    /**
     * For Verifying Otp
     *
     * @return Boolean
     */
    public Boolean verifyOtp(OtpDetail otpDetail) {
        log.info("Verify Otp Email Service called");
        try{
            Optional<OtpStore> storedOtp = otpRepository.findByOtp(otpDetail.getOtp());
            if (storedOtp.isPresent()) {
                log.info("OTP Verified  {}", otpDetail.getOtp());
                otpRepository.deleteAll();
                return true;
            } else {
                log.error("Verify Otp Email Service Throw PasswordNotMatchException Exception");
                throw new PasswordNotMatchException("Invalid Otp "+otpDetail.getOtp());
            }
        }
        catch (Exception e)
        {
            log.error("Exception In Send Email Service Exception {}", e.getMessage());
            throw e;
        }

    }

    /**
     * For Reset User Password
     *
     * @return Boolean
     */
    public Boolean resetUserPassword(ResetPassword resetPassword) {

        log.info("Reset User Password  Service called");
        try{
            Optional<Users> usersOptional = userRepository.findByEmail(resetPassword.getEmail());
            if (usersOptional.isPresent()) {
                 if(!resetPassword.getNewPassword().equals(resetPassword.getConfirmPassword()))
                 {
                     throw new PasswordNotMatchException("New Password And Confirm Password Is Not Matching");
                 }
                 usersOptional.get().setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
                 userRepository.save(usersOptional.get());
                  log.info("Reset User Password Successfully In Service ");
                return true;
            } else {
                log.error("Reset User Password Service Throw  UserNotFoundException");
                throw new UserNotFoundException("User Not Found");
            }
        }
        catch (Exception e)
        {
            log.error("Exception In Reset User Password Service Exception {}", e.getMessage());
            throw e;
        }

    }
}
