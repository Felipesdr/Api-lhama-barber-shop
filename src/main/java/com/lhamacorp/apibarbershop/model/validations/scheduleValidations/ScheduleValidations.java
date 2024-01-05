package com.lhamacorp.apibarbershop.model.validations.scheduleValidations;

import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ScheduleValidations {

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
}
