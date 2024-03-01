package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.infra.exception.ValidationException;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.AvailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.User;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private ScheduleRegisterDTO scheduleDTOBarberShopUnavailable, scheduleDTOBarberHasAnotherSchedule, scheduleRegisterDTOSuccessRandomBarber;
    private ScheduleRegisterDTO scheduleRegisterDTONoBarbersAvailable, scheduleRegisterDTOOneBarbersAvailable, scheduleRegisterDTOTwoBarbersAvailable, scheduleRegisterDTOAllBarbersAvailable;

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
        scheduleRegisterDTOSuccessRandomBarber = createScheduleDTOSuccessRandomBarber();
        scheduleRegisterDTONoBarbersAvailable = createScheduleRegisterDTONoBarbersAvailable();
        scheduleRegisterDTOOneBarbersAvailable = createScheduleRegisterDTOOneBarberAvailable();
        scheduleRegisterDTOTwoBarbersAvailable = createScheduleRegisterDTOTwoBarberAvailable();
        scheduleRegisterDTOAllBarbersAvailable = createScheduleRegisterDTOAllBarberAvailable();
    }

    @Test
    @DisplayName("Should return schedule id 6 if all went ok")
    @Transactional
    void registerScheduleCase1() {

        assertEquals(7L, scheduleService.registerSchedule(scheduleRegisterDTOSuccess, 4L));

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
    @DisplayName("Should return Id of a random available barber when barber id is not informed")
    @Transactional
    void registerScheduleCase9() {

        Long result = scheduleService.registerSchedule(scheduleRegisterDTOSuccessRandomBarber, 4L);
        result = scheduleRepository.getReferenceById(result).getBarber().getIdUser();
        assertThat(result, anyOf(is(1L), is(2L), is(3L)));

        Long result2 = scheduleService.registerSchedule(scheduleRegisterDTOSuccessRandomBarber, 4L);
        result2 = scheduleRepository.getReferenceById(result).getBarber().getIdUser();
        assertThat(result2, anyOf(is(1L), is(2L), is(3L), not(result)));

        Long result3 = scheduleService.registerSchedule(scheduleRegisterDTOSuccessRandomBarber, 4L);
        result3 = scheduleRepository.getReferenceById(result).getBarber().getIdUser();
        assertThat(result3, anyOf(is(1L), is(2L), is(3L), not(result), not(result2)));

        try{
            scheduleService.registerSchedule(scheduleRegisterDTOSuccessRandomBarber, 4L);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Nenhum barbeiro disponivel para essa data.", e.getMessage());
        }
    }


    @Test
    @DisplayName("Should return all available time from the barber shop")
    @Transactional
    void getAllAvailableTime() {
        LocalDate day = LocalDate.of(2025, 03,29);
        List<AvailableTimeDTO> availableTimes = createListAvailableTime();
        assertEquals(availableTimes, scheduleService.getAllAvailableTime(day));
    }


    @Test
    @DisplayName("Should return ValidationException with message: Nenhum barbeiro disponivel para essa data. when there is no avaiable barbers")
    @Transactional
    void getAllAvailableBarbersByALocalDateTimeCase1() {

        try{
            scheduleService.findAvailableBarbers(scheduleRegisterDTONoBarbersAvailable);
            fail("Validation exception was not throw");
        }catch (ValidationException e){
            assertEquals("Nenhum barbeiro disponivel para essa data.", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should return barber who's id is 1L (the only one available in this loocal date time")
    @Transactional
    void getAllAvailableBarbersByALocalDateTimeCase2() {

        List<User> availableBarbers= scheduleService.findAvailableBarbers(scheduleRegisterDTOOneBarbersAvailable);
        assertEquals(1, availableBarbers.size());
        assertEquals(1, availableBarbers.get(0).getIdUser());;
    }

    @Test
    @DisplayName("Should return list of 2 barbers with barber ids 2L and 1L (the only two available in this loocal date time")
    @Transactional
    void getAllAvailableBarbersByALocalDateTimeCase3() {

        List<User> availableBarbers= scheduleService.findAvailableBarbers(scheduleRegisterDTOTwoBarbersAvailable);
        assertEquals(2, availableBarbers.size());
        assertThat(availableBarbers.get(0).getIdUser(), anyOf(is(1L), is(2L)));
        assertThat(availableBarbers.get(1).getIdUser(), anyOf(is(1L), is(2L)));
    }

    @Test
    @DisplayName("Should return list of 3 barbers with barber ids 1L, 2L and 3L (the only two available in this loocal date time")
    @Transactional
    void getAllAvailableBarbersByALocalDateTimeCase4() {

        List<User> availableBarbers= scheduleService.findAvailableBarbers(scheduleRegisterDTOAllBarbersAvailable);
        assertEquals(3, availableBarbers.size());
        assertThat(availableBarbers.get(0).getIdUser(), anyOf(is(1L), is(2L), is(3L)));
        assertThat(availableBarbers.get(1).getIdUser(), anyOf(is(1L), is(2L), is(3L)));
        assertThat(availableBarbers.get(2).getIdUser(), anyOf(is(1L), is(2L), is(3L)));
    }

    // Private methods for test DTOs creation
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
        LocalDateTime start = LocalDateTime.of(2025, 03, 29, 9, 30, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOBarberHasAnotherSchedule(){

        LocalDateTime start = LocalDateTime.of(2025, 07, 07, 9, 00, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, 3L, 1L) ;
    }

    private ScheduleRegisterDTO createScheduleDTOSuccessRandomBarber(){

        LocalDateTime start = LocalDateTime.of(2025, 07, 03, 9, 00, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, null, 1L) ;
    }

    private List<AvailableTimeDTO> createListAvailableTime(){

        LocalDateTime[] availableTimes = {
                LocalDateTime.of(2025, 3, 29, 12, 0),
                LocalDateTime.of(2025, 3, 29, 12, 30),
                LocalDateTime.of(2025, 3, 29, 13, 30),
                LocalDateTime.of(2025, 3, 29, 14, 0),
                LocalDateTime.of(2025, 3, 29, 14, 30),
                LocalDateTime.of(2025, 3, 29, 15, 0),
                LocalDateTime.of(2025, 3, 29, 15, 30),
                LocalDateTime.of(2025, 3, 29, 16, 0),
                LocalDateTime.of(2025, 3, 29, 16, 30),
                LocalDateTime.of(2025, 3, 29, 17, 0),
                LocalDateTime.of(2025, 3, 29, 17, 30)
        };

        List<LocalDateTime> availableTimesList = new ArrayList<>();
        Collections.addAll(availableTimesList, availableTimes);
        List<AvailableTimeDTO> availableTimesDTOs = availableTimesList.stream().map(AvailableTimeDTO::new).toList();
        return availableTimesDTOs;
    }

    private ScheduleRegisterDTO createScheduleRegisterDTONoBarbersAvailable(){

        LocalDateTime start = LocalDateTime.of(2025, 03, 29, 13, 00, 00);
        return new ScheduleRegisterDTO(start, 30, 4L, null, 1L);

    }

    private ScheduleRegisterDTO createScheduleRegisterDTOOneBarberAvailable(){
        LocalDateTime start = LocalDateTime.of(2025, 3, 29, 13, 30, 0);
        return new ScheduleRegisterDTO(start, 30, 4L, null, 1L);
    }

    private ScheduleRegisterDTO createScheduleRegisterDTOTwoBarberAvailable(){
        LocalDateTime start = LocalDateTime.of(2025, 7, 7, 9, 0, 0);
        return new ScheduleRegisterDTO(start, 30, 4L, null, 1L);
    }

    private ScheduleRegisterDTO createScheduleRegisterDTOAllBarberAvailable(){
        LocalDateTime start = LocalDateTime.of(2025, 8, 7, 9, 0, 0);
        return new ScheduleRegisterDTO(start, 30, 4L, null, 1L);
    }





}