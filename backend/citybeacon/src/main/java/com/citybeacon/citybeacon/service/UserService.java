package com.citybeacon.citybeacon.service;

import com.citybeacon.citybeacon.User;
import com.citybeacon.citybeacon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByRole(String role) {
        // Simple mock of role filtering if needed, or just return all for now
        return userRepository.findAll().stream()
                .filter(u -> role.equals(u.getRole()))
                .toList();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserRole(Long userId, String role) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(role);
        return userRepository.save(user);
    }
}
