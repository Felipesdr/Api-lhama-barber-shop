package com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO;

import java.time.LocalDateTime;

public record AvailableTimeDTO(LocalDateTime availableTime) {

    public AvailableTimeDTO(LocalDateTime availableTime){

        this.availableTime = availableTime;

    }
}
