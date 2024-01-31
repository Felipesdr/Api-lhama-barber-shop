package com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO;

import com.lhamacorp.apibarbershop.model.Schedule;

import java.time.LocalDateTime;

public record ScheduleDTO(Long idSchedule, Long idClient, Long idService, Long idBarber, LocalDateTime start, LocalDateTime finish, Integer status) {

    public ScheduleDTO(Schedule schedule){
        this(
                schedule.getIdSchedule(),
                schedule.getClient().getIdUser(),
                schedule.getService().getIdService(),
                schedule.getBarber().getIdUser(),
                schedule.getStart(),
                schedule.getFinish(),
                schedule.getScheduleStatus()
                );
    }
}
