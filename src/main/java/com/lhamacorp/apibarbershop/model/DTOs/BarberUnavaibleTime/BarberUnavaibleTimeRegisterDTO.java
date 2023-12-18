package com.lhamacorp.apibarbershop.model.DTOs.BarberUnavaibleTime;

import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record BarberUnavaibleTimeRegisterDTO(
        String description,
        @Future @NotNull
        LocalDate startDate,
        @Future @NotNull
        LocalDate endDate,
        @Future @NotNull
        LocalTime startTime,
        @Future @NotNull
        LocalTime endTime,
        @NotNull
        BarberDTO barberDTO) {
}
