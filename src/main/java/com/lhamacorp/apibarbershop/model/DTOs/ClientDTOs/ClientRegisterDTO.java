package com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClientRegisterDTO(
        @NotNull
        String name,
        @NotNull
        String phone,
        @NotNull @Email
        String email) {
}
