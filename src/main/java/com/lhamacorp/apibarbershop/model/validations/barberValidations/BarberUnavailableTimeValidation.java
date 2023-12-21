package com.lhamacorp.apibarbershop.model.validations.barberValidations;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import jakarta.validation.ValidationException;

import java.time.temporal.ChronoUnit;

public class BarberUnavailableTimeValidation {

    public boolean validateBarber(ScheduleRegisterDTO data, BarberUnavailableTime unavailableTime){

        boolean afterUnavailableTimeStart = data.start().isAfter(unavailableTime.getStart());

        boolean beforeUnavailableTimeFinish = data.start().isBefore(unavailableTime.getFinish());

        boolean durinUnavailableTime = afterUnavailableTimeStart && beforeUnavailableTimeFinish;

        Long diferenceBetween = ChronoUnit.MINUTES.between(unavailableTime.getStart(),data.start());

        boolean closeToUnavailableTIme =  data.duration() > diferenceBetween && diferenceBetween > 0;

        if(durinUnavailableTime || closeToUnavailableTIme){

            return true;

        }

        return false;
    }
}
