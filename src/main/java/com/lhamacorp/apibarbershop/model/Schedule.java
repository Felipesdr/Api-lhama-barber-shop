package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
import com.lhamacorp.apibarbershop.model.ENUMs.ScheduleStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Schedule")
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;
    private LocalDateTime start;
    private LocalDateTime finish;
    @ManyToOne
    @JoinColumn(name = "idClient")
    private User client;
    @ManyToOne
    @JoinColumn(name = "idBarber")
    private User barber;
    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;


    public Schedule() {
    }

    public Schedule(Long idSchedule, LocalDateTime start, LocalDateTime finish, User client, User barber, Service service, ScheduleStatus status) {
        this.idSchedule = idSchedule;
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.status = status;
    }

    public Schedule(Long idSchedule, LocalDateTime start, LocalDateTime finish, User client, User barber, Service service) {
        this.idSchedule = idSchedule;
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.status = ScheduleStatus.PENDING;
    }

    public Schedule(ScheduleRegisterDTO scheduleRegisterDTO, User client, User barber, Service service){
        this.start = scheduleRegisterDTO.start();
        this.finish = scheduleRegisterDTO.start().plusMinutes(service.getDuration());
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.status = ScheduleStatus.PENDING;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(idSchedule, schedule.idSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSchedule);
    }
}

