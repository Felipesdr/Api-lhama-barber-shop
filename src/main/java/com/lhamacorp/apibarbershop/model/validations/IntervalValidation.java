package com.lhamacorp.apibarbershop.model.validations;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class IntervalValidation {


    public boolean ValidateInterval(LocalDateTime start, LocalDateTime finish, LocalDateTime timeBetwen){

        if(timeBetwen.isAfter(start) && timeBetwen.isBefore(finish) || timeBetwen.isEqual(start)) {

            return true;

        }

        return false;
    }
}
