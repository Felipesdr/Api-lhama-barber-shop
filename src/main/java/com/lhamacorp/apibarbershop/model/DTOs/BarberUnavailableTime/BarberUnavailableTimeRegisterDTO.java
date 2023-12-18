package com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record BarberUnavailableTimeRegisterDTO(

        String description,
        @Future @NotNull
        LocalDateTime start,
        @Future @NotNull
        LocalDateTime end,
        @NotNull
        Long idBarber) {
}
