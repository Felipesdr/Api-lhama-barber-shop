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

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerBarber(@RequestBody UserRegisterDTO request, UriComponentsBuilder uriBuilder){

        Long barberId = userService.registerUserBarber(request);

        URI uri = uriBuilder.path("/barber/register/{id}").buildAndExpand(barberId).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateBarber(@RequestBody UserUpdateDTO request){

        UserUpdateDTO barberUpdateDTO = userService.updateBarberOrClientById(request);

        return ResponseEntity.ok(barberUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteBarber(@PathVariable Long id){

        userService.deleteBarberOrClient(id);

        return ResponseEntity.noContent().build();
    }

}
