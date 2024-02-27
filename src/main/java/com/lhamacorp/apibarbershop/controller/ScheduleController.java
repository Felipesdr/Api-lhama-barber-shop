package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.infra.security.TokenService;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.AvailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.service.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.List;

@RestController
@RequestMapping("schedule")
@SecurityRequirement(name = "bearer-key")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerSchedule(@RequestBody @Valid ScheduleRegisterDTO request, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers){

        Long idRequestingUser = tokenService.getIdFromToken(headers);

        Long idSchedule = scheduleService.registerSchedule(request, idRequestingUser);

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

    @GetMapping("availableTime/{day}")
    public ResponseEntity<List<AvailableTimeDTO>> getAvailableTime(@PathVariable LocalDate day){

        return ResponseEntity.ok(scheduleService.getAllAvailableTime(day));

    }

    @PutMapping("confirm/{idSchedule}")
    @Transactional
    public ResponseEntity confirmSchedule(@PathVariable Long idSchedule){

        return ResponseEntity.ok(scheduleService.confirmScheduleById(idSchedule));
    }

    @PutMapping("start/{idSchedule}")
    @Transactional
    public ResponseEntity startSchedule(@PathVariable Long idSchedule){

        return ResponseEntity.ok(scheduleService.startScheduleById(idSchedule));
    }

    @PutMapping("finish/{idSchedule}")
    @Transactional
    public ResponseEntity finishSchedule(@PathVariable Long idSchedule){

        return ResponseEntity.ok(scheduleService.finishScheduleById(idSchedule));
    }

    @DeleteMapping("/calcel/{idSchedule}")
    @Transactional
    public ResponseEntity cancelScheduleById(@PathVariable Long idSchedule, @RequestHeader HttpHeaders headers){

        Long idRequestingUser = tokenService.getIdFromToken(headers);

        scheduleService.cancelScheduleById(idSchedule, idRequestingUser);

        return ResponseEntity.noContent().build();
    }

}
