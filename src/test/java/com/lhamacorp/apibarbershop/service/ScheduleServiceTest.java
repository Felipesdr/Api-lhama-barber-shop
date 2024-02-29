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
    private ScheduleRegisterDTO scheduleRegisterDTOIdServiceDontExist, scheduleRegisterDTOIdBarberDontExist, scheduleDTONotInBusinessHours;
    private ScheduleRegisterDTO scheduleDTOBarberShopUnavailable, scheduleDTOBarberHasAnotherSchedule;

    @BeforeEach
    void setUp() {

        scheduleRegisterDTOSuccess = createScheduleDTOSuccess();
        scheduleDTOIdClienteDontExist = createScheduleDTOIdClienteDontExist();
        scheduleDTOIdClientNotValid = createScheduleDTOIdClientNotValid();
        scheduleRegisterDTOIdServiceDontExist = createScheduleDTOIdServiceDontExist();
        scheduleRegisterDTOIdBarberDontExist = createScheduleDTOIdBarberDontExist();
        scheduleDTONotInBusinessHours = createScheduleDTONotInBusinessHours();
        scheduleDTOBarberShopUnavailable = createScheduleDTOBarberShopUnavailable();
        scheduleDTOBarberHasAnotherSchedule = createScheduleDTOBarberHasAnotherSchedule();
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
        try{
            scheduleService.registerSchedule(scheduleDTOIdClienteDontExist, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Id do cliente não encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Você não pode realizar essa operação com outro usuário. when a client try to register a schedule for another client")
    @Transactional
    void registerScheduleCase3() {
        try{
            scheduleService.registerSchedule(scheduleDTOIdClientNotValid, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Você não pode realizar essa operação com outro usuário.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Id do serviço não encontrado. when a service don't exists")
    @Transactional
    void registerScheduleCase4() {

        try{
            scheduleService.registerSchedule(scheduleRegisterDTOIdServiceDontExist, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Id do serviço não encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Id do barbeiro não encontrado. when a barber is not registered")
    @Transactional
    void registerScheduleCase5() {

        try{
            scheduleService.registerSchedule(scheduleRegisterDTOIdBarberDontExist, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Id do barbeiro não encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Agendamento fora de horário de funcionamento do estabelecimento when a schedule is outside of business hours")
    @Transactional
    void registerScheduleCase6() {

        try{
            scheduleService.registerSchedule(scheduleDTONotInBusinessHours, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Agendamento fora de horário de funcionamento do estabelecimento", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: A barbearia não estara funcionando nessa data when a schedule maches with a unavailable time")
    @Transactional
    void registerScheduleCase7() {

        try{
            scheduleService.registerSchedule(scheduleDTOBarberShopUnavailable, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("A barbearia não estara funcionando nessa data", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should return ValidationException wtih message: Esse barbeiro está indisponível para essa data. when a schedule maches with another schedule of the informed barber")
    @Transactional
    void registerScheduleCase8() {

        try{
            scheduleService.registerSchedule(scheduleDTOBarberHasAnotherSchedule, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Esse barbeiro está indisponível para essa data.", e.getMessage());
        }

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
        return new ScheduleRegisterDTO(start, 30, 4L, 1L, 12L) ;
    }
    private ScheduleRegisterDTO createScheduleDTOIdBarberDontExist(){
        LocalDateTime start = LocalDateTime.of(2024, 04, 29, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 14L, 1L) ;
    }
    private ScheduleRegisterDTO createScheduleDTONotInBusinessHours(){
        LocalDateTime start = LocalDateTime.of(2025, 04, 06, 14, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOBarberShopUnavailable(){
        LocalDateTime start = LocalDateTime.of(2025, 03, 24, 9, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOBarberHasAnotherSchedule(){

        LocalDateTime start = LocalDateTime.of(2025, 07, 07, 9, 00, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOSuccessRandomBarber(){

        LocalDateTime start = LocalDateTime.of(2025, 07, 06, 9, 00, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

}