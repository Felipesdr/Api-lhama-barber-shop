package com.lhamacorp.apibarbershop.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(
        @NotNull
        String name,
        @NotNull @Email
        String email,
        @NotNull
        String password) {
}
