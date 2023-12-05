package com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record BarberRegisterDTO(
        @NotNull
        String name,
        @NotNull @Email
        String email,
        @NotNull
        String phone,
        @NotNull
        String description) {
}
