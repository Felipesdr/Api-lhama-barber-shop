package com.lhamacorp.apibarbershop.model.validations.barberValidations;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.Schedule;
import com.lhamacorp.apibarbershop.model.validations.IntervalValidation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BarberUnavailableTimeValidation {

    private IntervalValidation intervalValidation = new IntervalValidation();

    public boolean validateBarber(ScheduleRegisterDTO data, LocalDateTime start, LocalDateTime finish) {

        boolean availability = validateBarberAvailability(data, start, finish);
        boolean notCloseToUnavailableTime = validateBarberCloseToUnavailableTime(data, start, finish);

        if(availability || notCloseToUnavailableTime) {

            return false;

        }

        return true;
    }


    private boolean validateBarberAvailability(ScheduleRegisterDTO data, LocalDateTime start, LocalDateTime finish) {

        LocalDateTime timeBetween = data.start();

        boolean duringUnavailableTime = intervalValidation.ValidateInterval(start, finish, timeBetween);

        if(duringUnavailableTime){

            return false;

        }

        return true;
    }

    private boolean validateBarberCloseToUnavailableTime(ScheduleRegisterDTO data, LocalDateTime start, LocalDateTime finish) {

        LocalDateTime scheduleTime = data.start();

        Long diferenceBetween = ChronoUnit.MINUTES.between(scheduleTime, start);

        boolean closeToUnavailableTIme =  data.duration() > diferenceBetween && diferenceBetween > 0;

        if(closeToUnavailableTIme){

            return false;

        }

        return true;
    }

}
