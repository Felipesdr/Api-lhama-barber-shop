package com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleRegisterDTO(
        @Future
        LocalDateTime start,
        Integer duration,
        @NotNull
        Long idClient,
        Long idBarber,
        @NotNull
        Long idService) {
}
