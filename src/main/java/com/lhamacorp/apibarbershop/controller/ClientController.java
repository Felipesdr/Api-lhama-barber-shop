package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.UserRole;
import com.lhamacorp.apibarbershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private UserService userService;

    @GetMapping("/clients")
    public ResponseEntity<List<UserDTO>> getAllActiveCliets(){

        return ResponseEntity.ok(userService.findAllUsersByActiveTrueAndRole(UserRole.CLIENT));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findClientByName(@PathVariable String name){

        return ResponseEntity.ok(userService.findUserByName(name, UserRole.CLIENT));

    }

    @GetMapping("/email/{email}")
    public ResponseEntity findClientByEmail(@PathVariable String email){

        System.out.println(email);
        return ResponseEntity.ok(userService.findUserByEmail(email, UserRole.CLIENT));
    }
}
