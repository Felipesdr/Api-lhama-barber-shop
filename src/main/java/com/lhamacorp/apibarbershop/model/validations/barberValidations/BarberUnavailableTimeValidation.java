package com.lhamacorp.apibarbershop.model.validations.barberValidations;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.Schedule;
import com.lhamacorp.apibarbershop.model.validations.IntervalValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BarberUnavailableTimeValidation {

    private IntervalValidation intervalValidation = new IntervalValidation();


    public boolean validateBarberUnavailableTime(ScheduleRegisterDTO data, BarberUnavailableTime unavailableTime) {

        LocalDateTime start = unavailableTime.getStart();
        LocalDateTime finish = unavailableTime.getFinish();
        LocalDateTime timeBetween = data.start();

        boolean durinUnavailableTime = intervalValidation.ValidateInterval(start, finish, timeBetween);

        Long diferenceBetween = ChronoUnit.MINUTES.between(start, timeBetween);

        boolean closeToUnavailableTIme =  data.duration() > diferenceBetween && diferenceBetween > 0;

        if(durinUnavailableTime || closeToUnavailableTIme){

            return true;

        }

        return false;
    }

    public boolean validateScheduleBarber(ScheduleRegisterDTO data, Schedule unavailableTime) {

        IntervalValidation intervalValidation1 = new IntervalValidation();
        return true;
    }

}
