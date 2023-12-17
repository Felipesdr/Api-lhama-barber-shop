package com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs;

import jakarta.validation.constraints.NotNull;

public record ServiceRegisterDTO(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        Integer duration,
        @NotNull
        Double price) {
}
