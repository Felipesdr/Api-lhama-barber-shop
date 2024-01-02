package com.lhamacorp.apibarbershop.model.validations;

import java.time.LocalDateTime;

public class IntervalValidation {


    public boolean ValidateInterval(LocalDateTime start, LocalDateTime finish, LocalDateTime timeBetwen){

        if(timeBetwen.isAfter(start) && timeBetwen.isBefore(finish)) {
            return true;
        }

        return false;
    }
}
