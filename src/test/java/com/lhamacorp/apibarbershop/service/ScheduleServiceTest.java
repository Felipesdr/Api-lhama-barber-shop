package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.ScheduleStatus;
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
import org.springframework.test.context.ActiveProfiles;

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
    private ScheduleRegisterDTO scheduleRegisterDTOSuccess;
    @BeforeEach
    void setUp() {

        scheduleRegisterDTOSuccess = createScheduleDTOSuccess();
    }

    @Test
    @DisplayName("Should return the new schedule id's if all went ok")
    @Transactional
    void registerScheduleCase1() {

        assertEquals(6L, scheduleService.registerSchedule(scheduleRegisterDTOSuccess, 4L));

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
}