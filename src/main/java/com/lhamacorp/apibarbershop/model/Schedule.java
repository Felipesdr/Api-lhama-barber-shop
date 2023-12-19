package com.lhamacorp.apibarbershop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Schedule")
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSchedule;
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBarber")
    private Barber barber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idService")
    private Service service;

    public Schedule() {
    }

    public Schedule(Long idSchedule, LocalDateTime dateTime, Client client, Barber barber, Service service) {
        this.idSchedule = idSchedule;
        this.dateTime = dateTime;
        this.client = client;
        this.barber = barber;
        this.service = service;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", dateTime=" + dateTime +
                ", client=" + client +
                ", barber=" + barber +
                ", service=" + service +
                '}';
    }
}
