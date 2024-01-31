package com.lhamacorp.apibarbershop.model.DTOs.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(
        @NotNull
        String name,
        @NotNull @Email
        String email,
        @NotNull
        String phone,
        @NotNull
        String password) {
}
