package com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BarberUnavailableTimeRegisterDTO(

        String description,
        @Future @NotNull
        LocalDateTime start,
        @Future @NotNull
        LocalDateTime end,
        @NotNull
        Long idBarber) {
}
