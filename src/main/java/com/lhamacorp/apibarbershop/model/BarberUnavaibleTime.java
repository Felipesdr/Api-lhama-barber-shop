package com.lhamacorp.apibarbershop.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity(name = "BarberUnavaibleTime")
@Table(name = "barber_unavaible_time")
public class BarberUnavaibleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarberUnavaibleTime;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "idBarber", nullable = false)
    private Barber barber;

    public BarberUnavaibleTime() {
    }

    public BarberUnavaibleTime(Long idBarberUnavaibleTime, String description, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Barber barber) {
        this.idBarberUnavaibleTime = idBarberUnavaibleTime;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.barber = barber;
    }

    public Long getIdBarberUnavaibleTime() {
        return idBarberUnavaibleTime;
    }

    public void setIdBarberUnavaibleTime(Long idBarberUnavaibleTime) {
        this.idBarberUnavaibleTime = idBarberUnavaibleTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarberUnavaibleTime that = (BarberUnavaibleTime) o;
        return Objects.equals(idBarberUnavaibleTime, that.idBarberUnavaibleTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBarberUnavaibleTime);
    }

    @Override
    public String toString() {
        return "BarberUnavaibleTime{" +
                "idBarberUnavaibleTime=" + idBarberUnavaibleTime +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", barber=" + barber +
                '}';
    }
}
