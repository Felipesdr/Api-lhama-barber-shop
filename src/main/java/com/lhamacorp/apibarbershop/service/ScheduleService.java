package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.exceptions.IdValidationException;
import com.lhamacorp.apibarbershop.model.*;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.ScheduleStatus;
import com.lhamacorp.apibarbershop.model.ENUMs.UserRole;
import com.lhamacorp.apibarbershop.model.validations.BarberValidation;
import com.lhamacorp.apibarbershop.model.validations.ScheduleValidations;
import com.lhamacorp.apibarbershop.repository.*;
import com.lhamacorp.apibarbershop.utilities.RandomPicker;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
public class ScheduleService {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BarberUnavailableTimeRepository barberUnavailableTimeRepository;
    @Autowired
    BarberValidation barberValidator;
    @Autowired
    ScheduleValidations scheduleValidator;

    public List<ScheduleDTO> findAllFutureSchedulesNotCanceledByIdBarber(Long idUserBarber){

        //Id schedule status 5 = canceled
        List<Schedule> scheduleList = scheduleRepository.findAllByBarberIdUserAndStartAfterAndStatusNot(idUserBarber, LocalDateTime.now(), ScheduleStatus.CANCELED);

        return scheduleList.stream().map(ScheduleDTO::new).toList();

    }


    public Long registerSchedule(ScheduleRegisterDTO scheduleRegisterData){

        //Validate idClient
        if(!userRepository.existsById(scheduleRegisterData.idClient())){
            throw new IdValidationException("Id do cliente não encontrado");
        }

        //Validate idService list
        if(!serviceRepository.existsById(scheduleRegisterData.idService())){
            throw new IdValidationException("Id do serviço não encontrado");
        }

        //Validate idBarber
        if(scheduleRegisterData.idBarber()!= null && !userRepository.existsById(scheduleRegisterData.idBarber())){
            throw new IdValidationException("Id do barbeiro não encontrado");
        }

        //Validate business hours
        scheduleValidator.businessHoursValidation(scheduleRegisterData);


        User client = userRepository.findById(scheduleRegisterData.idClient()).get();
        User barber = chooseBarber(scheduleRegisterData);
        Service service = serviceRepository.findById(scheduleRegisterData.idService()).get();
        Schedule schedule = new Schedule(scheduleRegisterData, client, barber, service);

        scheduleRepository.save(schedule);

        return schedule.getIdSchedule();
    }

    private User chooseBarber(ScheduleRegisterDTO scheduleRegisterData){

        //If idBarber is informed, choose barber by id
        if(scheduleRegisterData.idBarber() != null ){

            if(barberValidator.validateBarberById(scheduleRegisterData)){

                return userRepository.findById(scheduleRegisterData.idBarber()).get();

            }else{
                throw new ValidationException("Esse barbeiro está indisponível para essa data");
            }
        }

        //If idBarber is not informed choose random barber
        List<User> availableBarberList = findAvailableBarbers(scheduleRegisterData);

        return RandomPicker.getRandomElementFromCollection(availableBarberList);
    }

    public List<User> findAvailableBarbers(ScheduleRegisterDTO scheduleRegisterData){

        List<User> allBarbers = userRepository.findAllByActiveTrueAndRole(UserRole.BARBER);
        List<User> availableBarberList = new ArrayList<>();


        //Check wich barber is available
        for(User U: allBarbers){

            boolean barberUnavailableTime = false;
            boolean barberSchedule = false;

            List<BarberUnavailableTime> barberUnavailableTimeList = barberUnavailableTimeRepository.findAllByBarberIdUserAndActiveTrueAndStartAfter(U.getIdUser(), LocalDateTime.now());

            //If barber has no unavailable time, availableBarber = true
            if(barberUnavailableTimeList.isEmpty()) {

                barberUnavailableTime = true;

            }

            //If barber has no unavailable time conflicts with schedule start date time, availableBarber = true
            for(BarberUnavailableTime BUT : barberUnavailableTimeList) {
                if(barberValidator.validateBarber(scheduleRegisterData, BUT.getStart(), BUT.getFinish())) {

                    barberUnavailableTime = true;

                }else {

                    barberUnavailableTime = false;

                }

            }

            List<Schedule> futureSchedulesList = scheduleRepository.findAllByBarberIdUserAndStartAfterAndStatusNot(U.getIdUser(), LocalDateTime.now(), ScheduleStatus.CANCELED);

            //if barber has no future schedules, availableBarber = true. Id schedule status 5 = canceled
            if(futureSchedulesList.isEmpty()) {

                barberSchedule = true;

            }

            //if barber has no conflitcts with other future schedules, availableBarber = true
            for(Schedule S : futureSchedulesList) {
                if(barberValidator.validateBarber(scheduleRegisterData, S.getStart(), S.getFinish())) {

                    barberSchedule = true;

                }else{

                    barberSchedule = false;

                }
            }

            if(barberUnavailableTime == true && barberSchedule == true){

                availableBarberList.add(U);

            }

        }

        if(availableBarberList.isEmpty()){

            throw new ValidationException("Nenhum barbeiro disponivel para essa data!");

        }

        return availableBarberList;
    }
}
