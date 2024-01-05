package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.exceptions.IdValidationException;
import com.lhamacorp.apibarbershop.model.*;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleDTO;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.validations.barberValidations.BarberUnavailableTimeValidation;
import com.lhamacorp.apibarbershop.repository.*;
import com.lhamacorp.apibarbershop.utilities.RandomPicker;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private BarberUnavailableTimeRepository barberUnavailableTimeRepository;

    public List<ScheduleDTO> findAllFutureSchedulesNotCanceledByIdBarber(Long idBarber){

        //Id schedule status 5 = caanceled
        List<Schedule> scheduleList = scheduleRepository.findAllByBarberIdBarberAndStartAfterAndIdScheduleStatusNot(idBarber, LocalDateTime.now(), 5);

        return scheduleList.stream().map(ScheduleDTO::new).toList();

    }


    public Long registerSchedule(ScheduleRegisterDTO scheduleRegisterData){

        //Validate idClient
        if(!clientRepository.existsById(scheduleRegisterData.idClient())){
            throw new IdValidationException("Id do cliente não encontrado");
        }

        //Validate idService list
        if(!serviceRepository.existsById(scheduleRegisterData.idService())){
            throw new IdValidationException("Id do serviço não encontrado");
        }

        //Validate idBarber
        if(scheduleRegisterData.idBarber()!= null && !barberRepository.existsById(scheduleRegisterData.idBarber())){
            throw new IdValidationException("Id do barbeiro não encontrado");
        }
        Client client = clientRepository.findById(scheduleRegisterData.idClient()).get();
        Barber barber = chooseBarber(scheduleRegisterData);
        Service service = serviceRepository.findById(scheduleRegisterData.idService()).get();
        Schedule schedule = new Schedule(scheduleRegisterData, client, barber, service);

        scheduleRepository.save(schedule);

        return schedule.getIdSchedule();
    }

    private Barber chooseBarber(ScheduleRegisterDTO scheduleRegisterData){

        //If idBarber is informed, choose barber by id
        if(scheduleRegisterData.idBarber() != null){
            return barberRepository.findById(scheduleRegisterData.idBarber()).get();
        }

        //If idBarber is not informed choose random barber
        List<Barber> availableBarberList = findAvailableBarbers(scheduleRegisterData);

        return RandomPicker.getRandomElementFromCollection(availableBarberList);
    }

    public List<Barber> findAvailableBarbers(ScheduleRegisterDTO scheduleRegisterData){

        BarberUnavailableTimeValidation barberValidator = new BarberUnavailableTimeValidation();

        List<Barber> allBarbers = barberRepository.findAllByActiveTrue();
        List<Barber> availableBarberList = new ArrayList<>();


        //Check wich barber is available
        for(Barber B: allBarbers){

            boolean barberUnavailableTime = false;
            boolean barberSchedule = false;

            List<BarberUnavailableTime> barberUnavailableTimeList = barberUnavailableTimeRepository.findAllByBarberIdBarberAndActiveTrueAndStartAfter(B.getIdBarber(), LocalDateTime.now());

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

            List<Schedule> futureSchedulesList = scheduleRepository.findAllByBarberIdBarberAndStartAfterAndIdScheduleStatusNot(B.getIdBarber(), LocalDateTime.now(), 5);

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

                availableBarberList.add(B);

            }

        }

        if(availableBarberList.isEmpty()){

            throw new ValidationException("Nenhum barbeiro disponivel para essa data!");

        }

        return availableBarberList;
    }
}
