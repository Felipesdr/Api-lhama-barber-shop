package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public URI registerUser(UserRegisterDTO registerUserData, UriComponentsBuilder uriBuilder){

        User newUser = new User(registerUserData);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);

        URI uri = uriBuilder.path("user/{id}").buildAndExpand(newUser.getIdUser()).toUri();

        return uri;
    }
}
