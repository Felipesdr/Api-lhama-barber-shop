package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberUpdateDTO;
import com.lhamacorp.apibarbershop.service.BarberService;
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
    private BarberService barberService;

    @GetMapping("/barbers")
    public ResponseEntity<List<BarberDTO>> getAllActiveBarbers(){

        List<BarberDTO> list = barberService.findAllBarbersByActiveTrue();
        return ResponseEntity.ok(list);
    }
    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerBarber(@RequestBody BarberRegisterDTO request, UriComponentsBuilder uriBuilder){

        URI uri = barberService.registerBarber(request, uriBuilder);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateBarber(@RequestBody BarberUpdateDTO request){

        BarberUpdateDTO barberUpdateDTO = barberService.updateBarber(request);

        return ResponseEntity.ok(barberUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteBarber(@PathVariable Long id){

        barberService.deleteBarber(id);

        return ResponseEntity.noContent().build();
    }

}
