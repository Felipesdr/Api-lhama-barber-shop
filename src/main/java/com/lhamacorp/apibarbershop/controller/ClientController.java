package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserUpdateDTO;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerClient (@RequestBody @Valid UserRegisterDTO request, UriComponentsBuilder uriBuilder){

        Long idClient =  userService.registerUserClient(request);

        if(idClient == null){

            return ResponseEntity.badRequest().build();
        }

        URI uri = uriBuilder.path("client/register/{id}").buildAndExpand(idClient).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity deleteClient(@PathVariable Long id){

        userService.deleteBarberOrClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateClientById(@RequestBody @Valid UserUpdateDTO request){

        UserUpdateDTO client = userService.updateUser(request);

        return ResponseEntity.ok(client);
    }

}
