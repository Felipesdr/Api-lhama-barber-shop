package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserUpdateDTO;
import com.lhamacorp.apibarbershop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

            System.out.printf("============================== caiu aqui babaca =================================");
            return ResponseEntity.badRequest().build();

        }

        URI uri = uriBuilder.path("register/client/{id}").buildAndExpand(userService.registerUserClient(userRegisterData)).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PostMapping("/register/barber")
    @Transactional
    public ResponseEntity registerUserBarber(@RequestBody UserRegisterDTO userRegisterData, UriComponentsBuilder uriBuilder){

        if(userService.registerUserBarber(userRegisterData) == null){

            return ResponseEntity.badRequest().build();
        }

        URI uri = uriBuilder.path("register/{id}").buildAndExpand(userService.registerUserBarber(userRegisterData)).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/account")
    @Transactional
    public ResponseEntity updateUserAccountData(@RequestBody UserUpdateDTO userUpdateData, @RequestHeader HttpHeaders header){

        UserUpdateDTO userUpdated = userService.updateUserOwnAccount(userUpdateData, header);

        if(userUpdated == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userUpdated);

    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateUser(@RequestBody UserUpdateDTO userUpdateData){

        UserUpdateDTO userUpdated = userService.updateUser(userUpdateData);

        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("delete/{idUser}")
    @Transactional
    public ResponseEntity deleteBarberOrClient(@PathVariable Long idUser){

        userService.deleteBarberOrClient(idUser);

        return ResponseEntity.noContent().build();
    }

}
