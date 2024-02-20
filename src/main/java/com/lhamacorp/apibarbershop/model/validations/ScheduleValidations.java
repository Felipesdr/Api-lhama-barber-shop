package com.lhamacorp.apibarbershop.model.validations;

import com.lhamacorp.apibarbershop.infra.security.TokenService;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.UserRole;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ScheduleValidations {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    public void businessHoursValidation(ScheduleRegisterDTO scheduleData){

        var scheduleDay = scheduleData.start().getDayOfWeek();

        var scheduleHour = scheduleData.start().getHour();

        boolean saturdayBusinessRules = scheduleHour < 9 || scheduleHour > 13.15;

        boolean daysOfTheWeekBusinessRules = scheduleHour < 9 || scheduleHour > 18.15;

        boolean sunday = scheduleDay.equals(DayOfWeek.SUNDAY);

        if(scheduleDay.equals(DayOfWeek.SATURDAY)){

            if(saturdayBusinessRules){
                throw new ValidationException("Agendamento fora de horário de funcionamento do estabelecimento");
            }
        }

        if(daysOfTheWeekBusinessRules || sunday){

            throw new ValidationException("Agendamento fora de horário de funcionamento do estabelecimento");
        }

    }

    public void idClientValidation(ScheduleRegisterDTO scheduleRegisterDTO, HttpHeaders headers){

        Long userRequestingId = tokenService.getIdFromToken(headers);

        User requestingUser = userRepository.getReferenceById(userRequestingId);

        Long schedulingUserId = scheduleRegisterDTO.idClient();


        if(requestingUser.getRole() == UserRole.CLIENT){

            if(userRequestingId != schedulingUserId){

                throw new RuntimeException("Você não pode fazer isso!");
            }
        }

    }


}
