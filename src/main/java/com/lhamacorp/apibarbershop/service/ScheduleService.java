package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.exceptions.IdValidationException;
import com.lhamacorp.apibarbershop.model.*;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.validations.barberValidations.BarberUnavailableTimeValidation;
import com.lhamacorp.apibarbershop.repository.*;
import com.lhamacorp.apibarbershop.utilities.RandomPicker;
import org.springframework.beans.factory.annotation.Autowired;


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

    public List<Schedule> findAllScheduleByBarberid;

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

        BarberUnavailableTimeValidation barberValidator = new BarberUnavailableTimeValidation();

        //If idBarber is informed, choose barber by id
        if(scheduleRegisterData.idBarber() != null){
            return barberRepository.findById(scheduleRegisterData.idBarber()).get();
        }

        //If idBarber is not informed choose random barber
        List<Barber> allBarbers = barberRepository.findAllByActiveTrue();
        List<Barber> availableBarberList = new ArrayList<>();

        //Check wich barber is available
        for(Barber B: allBarbers){

            //If barber has no unavailable time, add it to available barbers list
            List<BarberUnavailableTime> tempList = barberUnavailableTimeRepository.findAllByBarberIdBarberAndActiveTrue(B.getIdBarber());
            if(tempList.isEmpty()) {

                availableBarberList.add(B);

            }

            boolean availableBarber;


            //If barber has no unavailable time conflicts with schedule date time, add it to available barbers list
            for(BarberUnavailableTime BUT : tempList) {
                if(barberValidator.validateBarberUnavailableTime(scheduleRegisterData, BUT)) {
                } else {

                    availableBarberList.add(B);

                }
            }
        }
        availableBarberList.forEach(System.out::println);
        //Return random available barber
        return RandomPicker.getRandomElementFromCollection(availableBarberList);
    }
}
