package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.scheduleDTO.ScheduleRegisterDTO;
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
    private Client client;
    @ManyToOne
    @JoinColumn(name = "idBarber")
    private Barber barber;
    @ManyToOne
    @JoinColumn(name = "idService")
    private Service service;

    private Integer idScheduleStatus;


    public Schedule() {
    }

    public Schedule(Long idSchedule, LocalDateTime start, LocalDateTime finish, Client client, Barber barber, Service service, Integer idScheduleStatus) {
        this.idSchedule = idSchedule;
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.idScheduleStatus = idScheduleStatus;
    }

    public Schedule(Long idSchedule, LocalDateTime start, LocalDateTime finish, Client client, Barber barber, Service service) {
        this.idSchedule = idSchedule;
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.idScheduleStatus = 1;
    }

    public Schedule(ScheduleRegisterDTO scheduleRegisterDTO, Client client, Barber barber, Service service){
        this.start = scheduleRegisterDTO.start();
        this.finish = scheduleRegisterDTO.start().plusMinutes(service.getDuration());
        this.client = client;
        this.barber = barber;
        this.service = service;
        this.idScheduleStatus = 1;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getScheduleStatus() {
        return idScheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.idScheduleStatus = scheduleStatus;
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

