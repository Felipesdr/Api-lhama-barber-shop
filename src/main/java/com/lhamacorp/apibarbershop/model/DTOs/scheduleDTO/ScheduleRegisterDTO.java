package com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ScheduleRegisterDTO(
        @Future
        LocalDateTime dateTime,
        @NotNull
        Long idClient,
        Long idBarber,
        @NotNull
        List<Long> idServices) {
}
