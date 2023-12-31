package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.service.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity registerSchedule(@RequestBody ScheduleRegisterDTO request, UriComponentsBuilder uriBuilder){
        Long idSchedule = scheduleService.registerSchedule(request);


        URI uri = uriBuilder.path("/register/{idSchedule}").buildAndExpand(idSchedule).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/future/{idBarber}")
    public ResponseEntity<List<ScheduleDTO>> findAllFutureScheduleByIdBarber(@PathVariable Long idBarber){

        List<ScheduleDTO> futureScheduleDtoList = scheduleService.findAllFutureSchedulesNotCanceledByIdBarber(idBarber);

        return ResponseEntity.ok(futureScheduleDtoList);
    }

    @GetMapping
    public ResponseEntity<List<BarberDTO>> findAllAvailableBarbers(@RequestParam LocalDateTime start, @RequestParam Long idClient, @RequestParam Long idService, @RequestParam Integer duration){

        ScheduleRegisterDTO scheduleRegisterDTO = new ScheduleRegisterDTO(start, duration, idClient, null, idService);

        List<BarberDTO> barberDTOList = scheduleService.findAvailableBarbers(scheduleRegisterDTO).stream().map(BarberDTO::new).toList();

        return ResponseEntity.ok(barberDTOList);
    }
}
