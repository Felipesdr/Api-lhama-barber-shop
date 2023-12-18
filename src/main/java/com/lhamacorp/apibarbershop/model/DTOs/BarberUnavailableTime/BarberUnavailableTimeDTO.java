package com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTime;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;

import java.time.LocalDateTime;

public record BarberUnavailableTimeDTO(Long idBarberUnavailableTime, String description, LocalDateTime start,LocalDateTime finish) {

    public BarberUnavailableTimeDTO(BarberUnavailableTime barberUnavailableTime){
        this(
                barberUnavailableTime.getIdBarberUnavailableTime(),
                barberUnavailableTime.getDescription(),
                barberUnavailableTime.getStart(),
                barberUnavailableTime.getFinish());
    }
}
