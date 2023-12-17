package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientDTO;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientUpdateDTO;
import com.lhamacorp.apibarbershop.service.ClientService;
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
    private ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllActiveCliets(){

        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerClient (@RequestBody @Valid ClientRegisterDTO request, UriComponentsBuilder uriBuilder){

        URI uri = clientService.registerClient(request, uriBuilder);

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity deleteClient(@PathVariable Long id){

        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateClientById(@RequestBody @Valid ClientUpdateDTO request){

        ClientUpdateDTO client = clientService.updateClientById(request);

        return ResponseEntity.ok(client);
    }


}
