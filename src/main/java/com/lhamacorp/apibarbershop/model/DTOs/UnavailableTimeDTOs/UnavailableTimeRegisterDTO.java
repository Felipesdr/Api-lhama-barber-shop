package com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record UnavailableTimeRegisterDTO(

        String description,
        @Future
        LocalDateTime start,
        @Future
        LocalDateTime finish) {
}
