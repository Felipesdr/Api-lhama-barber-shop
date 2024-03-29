package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.service.BarberUnavaibleTimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("barberUnavailableTime")
@SecurityRequirement(name = "bearer-key")
public class BarberUnavaibleTimeController {
    @Autowired
    private BarberUnavaibleTimeService service;

   @PostMapping("/register")
   @Transactional
   public ResponseEntity registerBarberUnavaibleTime(@RequestBody @Valid BarberUnavailableTimeRegisterDTO request, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers){

       Long idBarberUnavailableTime = service.registerBarberUnavaibleTime(request, headers);

       URI uri = uriBuilder.path("barberUnavailableTime/register/{id}").buildAndExpand(idBarberUnavailableTime).toUri();

       return ResponseEntity.created(uri).build();
   }

   @GetMapping("/{idBarber}")
   public ResponseEntity findAllBarberUnavailableTimeByIdBarberAndActiveTrue(@PathVariable Long idBarber, @RequestHeader HttpHeaders headers){

       List<BarberUnavailableTimeDTO> list = service.findAllFutureBarberUnavailableTimeByIdBarberAndActiveTrue(idBarber, headers);

       return ResponseEntity.ok(list);
   }

   @PutMapping("update")
   @Transactional
   public ResponseEntity updateBarberUnavailableTimeById(@RequestBody BarberUnavailableTimeDTO request, @RequestHeader HttpHeaders headers){

       BarberUnavailableTimeDTO updatedBarberUnavailableTime = service.updateBarberUnavailableTime(request, headers);

       return ResponseEntity.ok(updatedBarberUnavailableTime);
   }

   @DeleteMapping("delete/{idBarberUnavailableTime}")
   @Transactional
   public ResponseEntity deleBarberUnavailableTimeById(@PathVariable Long idBarberUnavailableTime, @RequestHeader HttpHeaders headers){

       service.deleteBarberUnavailableTimeById(idBarberUnavailableTime, headers);

       return ResponseEntity.noContent().build();
   }


}
