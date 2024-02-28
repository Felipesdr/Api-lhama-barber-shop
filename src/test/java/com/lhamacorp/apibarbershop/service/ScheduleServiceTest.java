package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.infra.exception.ValidationException;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.validations.BarberValidation;
import com.lhamacorp.apibarbershop.model.validations.IntervalValidation;
import com.lhamacorp.apibarbershop.model.validations.ScheduleValidation;
import com.lhamacorp.apibarbershop.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BarberUnavailableTimeRepository barberUnavailableTimeRepository;
    @Autowired
    private UnavailableTimeRepository unavailableTimeRepository;
    @Autowired
    private BarberValidation barberValidator;
    @Autowired
    private ScheduleValidation scheduleValidator;
    @Autowired
    private IntervalValidation intervalValidator;
    private ScheduleRegisterDTO scheduleRegisterDTOSuccess, scheduleDTOIdClienteDontExist, scheduleDTOIdClientNotValid;
    private ScheduleRegisterDTO scheduleRegisterDTOIdServiceDontExist, scheduleRegisterDTOIdBarberDontExist;

    @BeforeEach
    void setUp() {

        scheduleRegisterDTOSuccess = createScheduleDTOSuccess();
        scheduleDTOIdClienteDontExist = createScheduleDTOIdClienteDontExist();
        scheduleDTOIdClientNotValid = createScheduleDTOIdClientNotValid();
        scheduleRegisterDTOIdServiceDontExist = createScheduleDTOIdServiceDontExist();
        scheduleRegisterDTOIdBarberDontExist = createScheduleDTOIdBarberDontExist();
    }

    @Test
    @DisplayName("Should return schedule id 6 if all went ok")
    @Transactional
    void registerScheduleCase1() {

        assertEquals(6L, scheduleService.registerSchedule(scheduleRegisterDTOSuccess, 4L));

    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Id do cliente não encontrado. When clients id dosen't exist")
    @Transactional
    void registerScheduleCase2() {

        assertThrowsExactly(ValidationException.class,
                ()-> scheduleService.registerSchedule(scheduleDTOIdClienteDontExist, 4L),
                "Id do cliente não encontrado.");
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Você não pode realizar essa operação com outro usuário. when a client try to register a schedule for another client")
    @Transactional
    void registerScheduleCase3() {

        assertThrowsExactly(ValidationException.class,
                ()-> scheduleService.registerSchedule(scheduleDTOIdClientNotValid, 4L),
                "Você não pode realizar essa operação com outro usuário.");
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Id do serviço não encontrado. when a service don't exists")
    @Transactional
    void registerScheduleCase4() {

        assertThrowsExactly(ValidationException.class,
                ()-> scheduleService.registerSchedule(scheduleRegisterDTOIdServiceDontExist, 4L),
                "Id do serviço não encontrado.");
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Id do serviço não encontrado. when a service don't exists")
    @Transactional
    void registerScheduleCase5() {

        assertThrowsExactly(ValidationException.class,
                ()-> scheduleService.registerSchedule(scheduleRegisterDTOIdBarberDontExist, 4L),
                "Id do barbeiro não encontrado.");
    }



    @Test
    void findAvailableBarbers() {
    }

    @Test
    void getAllAvailableTime() {
    }

    private ScheduleRegisterDTO createScheduleDTOSuccess(){

        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 1L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOIdClienteDontExist(){

        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 10L, 1L, 1L) ;

    }

    private ScheduleRegisterDTO createScheduleDTOIdClientNotValid(){

        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 5L, 1L, 1L) ;
    }
    private ScheduleRegisterDTO createScheduleDTOIdServiceDontExist(){

        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 5L, 1L, 12L) ;
    }
    private ScheduleRegisterDTO createScheduleDTOIdBarberDontExist(){

        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 5L, 14L, 1L) ;
    }
}