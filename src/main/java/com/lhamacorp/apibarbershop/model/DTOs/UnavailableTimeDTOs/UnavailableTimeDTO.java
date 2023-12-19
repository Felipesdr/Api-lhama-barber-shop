package com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs;

import com.lhamacorp.apibarbershop.model.UnavailableTime;

import java.time.LocalDateTime;

public record UnavailableTimeDTO(Long idUnavailableTime, String description, LocalDateTime start, LocalDateTime finish) {
    public UnavailableTimeDTO(UnavailableTime unavailableTimeUpdated) {
        this(
                unavailableTimeUpdated.getIdUnavailableTime(),
                unavailableTimeUpdated.getDescription(),
                unavailableTimeUpdated.getStart(),
                unavailableTimeUpdated.getFinish());
    }
}
