package com.lhamacorp.apibarbershop.model.validations;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.Schedule;
import com.lhamacorp.apibarbershop.repository.BarberUnavailableTimeRepository;
import com.lhamacorp.apibarbershop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class BarberValidation {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BarberUnavailableTimeRepository barberUnavailableTimeRepository;

    @Autowired
    private IntervalValidation intervalValidation;

    public boolean validateBarberById(ScheduleRegisterDTO data){

        List<BarberUnavailableTime> barberUnavailableTimeList = barberUnavailableTimeRepository.findAllByBarberIdUserAndActiveTrueAndStartAfter(data.idBarber(), LocalDateTime.now());

        for(BarberUnavailableTime UT : barberUnavailableTimeList){

            if(!validateBarberAvailability(data, UT.getFinish(), data.start())){

                return false;
            }

            if(!validateBarberCloseToUnavailableTime(data, UT.getStart())){

                return false;

            }
        }

        List<Schedule> barberScheduleList = scheduleRepository.findAllByBarberIdUserAndStartAfterAndIdScheduleStatusNot(data.idBarber(), LocalDateTime.now(), 5);

        for(Schedule S : barberScheduleList){

            if(!validateBarberAvailability(data, S.getStart(), S.getFinish())){

                return false;

            }

            if(!validateBarberCloseToUnavailableTime(data, S.getStart())){

                return false;

            }
        }

        return true;
    }

    public boolean validateBarber(ScheduleRegisterDTO data, LocalDateTime start, LocalDateTime finish) {

        boolean availability = validateBarberAvailability(data, start, finish);
        boolean notCloseToUnavailableTime = validateBarberCloseToUnavailableTime(data, start);

        if(!availability || !notCloseToUnavailableTime) {

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

    private boolean validateBarberCloseToUnavailableTime(ScheduleRegisterDTO data, LocalDateTime start) {

        LocalDateTime scheduleTime = data.start();

        Long diferenceBetween = ChronoUnit.MINUTES.between(scheduleTime, start);

        boolean closeToUnavailableTIme =  data.duration() > diferenceBetween && diferenceBetween > 0;

        if(closeToUnavailableTIme){

            return false;

        }

        return true;
    }

}
