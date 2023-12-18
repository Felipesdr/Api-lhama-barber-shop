package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTime.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.service.BarberUnavaibleTimeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("barberUnavaibleTime")
public class BarberUnavaibleTimeController {
    @Autowired
    private BarberUnavaibleTimeService service;

   @PostMapping("/register")
   @Transactional
   public ResponseEntity registerBarberUnavaibleTime(@RequestBody @Valid BarberUnavailableTimeRegisterDTO request, UriComponentsBuilder uriBuilder){

       URI uri = service.registerBarberUnavaibleTime(request, uriBuilder);

       return ResponseEntity.created(uri).build();
   }
}
