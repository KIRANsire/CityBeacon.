package com.citybeacon.citybeacon.service;

import com.citybeacon.citybeacon.User;
import com.citybeacon.citybeacon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void generateOtp(String phone) {
        Optional<User> userOpt = userRepository.findByPhone(phone);
        User user = userOpt.orElseGet(() -> {
            User newUser = new User();
            newUser.setPhone(phone);
            newUser.setRole("USER"); // Default role
            return newUser;
        });

        String otp = String.format("%06d", new Random().nextInt(1000000));
        user.setOtp(otp);
        userRepository.save(user);
        
        // TODO: Integrate Twilio or Email to send OTP
        System.out.println("OTP for " + phone + ": " + otp);
    }

    public Optional<User> verifyOtp(String phone, String otp) {
        Optional<User> userOpt = userRepository.findByPhoneAndOtp(phone, otp);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setVerified(true);
            user.setOtp(null); // Clear OTP after verification
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
