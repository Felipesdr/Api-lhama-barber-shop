package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.service.ScheduleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerSchedule(@RequestBody @Valid ScheduleRegisterDTO request, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers){
        Long idSchedule = scheduleService.registerSchedule(request, headers);

        URI uri = uriBuilder.path("/register/{idSchedule}").buildAndExpand(idSchedule).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/future/{idBarber}")
    public ResponseEntity<List<ScheduleDTO>> findAllFutureScheduleByIdBarber(@PathVariable Long idBarber){

        List<ScheduleDTO> futureScheduleDtoList = scheduleService.findAllFutureSchedulesNotCanceledByIdBarber(idBarber);

        return ResponseEntity.ok(futureScheduleDtoList);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllAvailableBarbers(@RequestParam LocalDateTime start, @RequestParam Long idClient, @RequestParam Long idService, @RequestParam Integer duration){

        ScheduleRegisterDTO scheduleRegisterDTO = new ScheduleRegisterDTO(start, duration, idClient, null, idService);

        List<UserDTO> barberDTOList = scheduleService.findAvailableBarbers(scheduleRegisterDTO).stream().map(UserDTO::new).toList();

        return ResponseEntity.ok(barberDTOList);
    }
}
