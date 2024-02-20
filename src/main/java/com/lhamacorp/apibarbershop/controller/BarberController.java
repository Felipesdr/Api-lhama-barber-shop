package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.UserRole;
import com.lhamacorp.apibarbershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
