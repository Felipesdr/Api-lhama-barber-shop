package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserUpdateDTO;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/barber")
public class BarberController {

    @Autowired
    private UserService userService;

    @GetMapping("/barbers")
    public ResponseEntity<List<UserDTO>> getAllActiveBarbers(){

        List<UserDTO> list = userService.findAllUsersByActiveTrueAndRole(UserRole.BARBER);
        return ResponseEntity.ok(list);

    }


}
