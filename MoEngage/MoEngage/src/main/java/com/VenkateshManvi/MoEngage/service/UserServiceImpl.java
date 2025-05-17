package com.VenkateshManvi.MoEngage.service;

import com.VenkateshManvi.MoEngage.Model.User;
import com.VenkateshManvi.MoEngage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private  final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public User save(User user) {

        Optional<User> existingUser= userRepository.findByUsername(user.getUsername());
        Optional<User> existingEmail=userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw  new RuntimeException("username is already taken");
        }
        if(existingEmail.isPresent()){
            throw  new RuntimeException("Email is already Used");
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }
}
