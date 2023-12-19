package com.lhamacorp.apibarbershop.controller;


import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.service.UnavailableTimeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("unavailableTime")
public class UnavailableTimeController {

    @Autowired
    private UnavailableTimeService service;

    @PostMapping("register")
    @Transactional
    public ResponseEntity registerUnavailableTime(@RequestBody UnavailableTimeRegisterDTO request, UriComponentsBuilder uriBuilder) {

        URI uri = service.registerUnavailableTime(request, uriBuilder);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity getAllUnavailableTimes(@RequestParam(required = false) LocalDateTime start,
                                                 @RequestParam(required = false) LocalDateTime finish) {
        if (start == null || finish == null) {

            return ResponseEntity.ok(service.findAllUnavailableTime());
        }
        return ResponseEntity.ok(service.findUnavailableTimesBetweenGap(start, finish));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateUnavailableTime(@RequestBody UnavailableTimeDTO request) {

        return ResponseEntity.ok(service.updateUnavailableTime(request));
    }

    @DeleteMapping("/delete/{idUnavailableTime}")
    @Transactional
    public ResponseEntity deleteUnavailableTimeById(Long idUnavailableTime){

        service.deleteUnavailableTimeById(idUnavailableTime);

        return ResponseEntity.noContent().build();
    }
}
