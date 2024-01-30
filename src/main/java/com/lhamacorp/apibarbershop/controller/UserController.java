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

    @PostMapping("/register/client")
    @Transactional
    public ResponseEntity registerUserClient(@RequestBody UserRegisterDTO userRegisterData, UriComponentsBuilder uriBuilder){

        if(userService.registerUserClient(userRegisterData) == null){

            return ResponseEntity.badRequest().build();
        }

        URI uri = uriBuilder.path("register/{id}").buildAndExpand(userService.registerUserClient(userRegisterData)).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/register/barber")
    @Transactional
    public ResponseEntity registerUserBarber(@RequestBody UserRegisterDTO userRegisterData, UriComponentsBuilder uriBuilder){

        if(userService.registerUserClient(userRegisterData) == null){

            return ResponseEntity.badRequest().build();
        }

        URI uri = uriBuilder.path("register/{id}").buildAndExpand(userService.registerUserBarber(userRegisterData)).toUri();

        return ResponseEntity.created(uri).build();
    }
}
