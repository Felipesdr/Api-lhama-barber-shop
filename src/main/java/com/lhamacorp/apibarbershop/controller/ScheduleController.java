package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerSchedule(@RequestBody ScheduleRegisterDTO request, UriComponentsBuilder uriBuilder){
        Long idSchedule = scheduleService.registerSchedule(request);


        URI uri = uriBuilder.path("/register/{idSchedule}").buildAndExpand(idSchedule).toUri();

        return ResponseEntity.created(uri).build();
    }
}
