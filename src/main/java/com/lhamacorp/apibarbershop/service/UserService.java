package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User registerUser(UserRegisterDTO registerUserData) {

        if(userRepository.existsByEmail(registerUserData.email())){

            return null;
        }

        User newUser = new User(registerUserData);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return newUser;
    }

    public Long registerUserClient(UserRegisterDTO registerUserData){

        User newUser = this.registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            return savedUser.getIdUser();
        } else {
            return null;
        }

    }

    public Long registerUserBarber(UserRegisterDTO registerUserData){

        User newUser = registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            savedUser.setRole(UserRole.BARBER);

            return savedUser.getIdUser();
        } else {
            return null;
        }

    }

}
