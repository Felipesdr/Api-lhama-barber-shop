package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.UserRegisterDTO;
import com.lhamacorp.apibarbershop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @Transactional
    public ResponseEntity registerUser(@RequestBody UserRegisterDTO userRegisterData, UriComponentsBuilder uriBuilder){

        URI uri = userService.registerUser(userRegisterData, uriBuilder);

        return ResponseEntity.created(uri).build();
    }
}
