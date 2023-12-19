package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeRegisterDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "UnavailableTime")
@Table(name = "unavailableTime")
public class UnavailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnavailableTime;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    private boolean active;

    public UnavailableTime() {
    }

    public UnavailableTime(Long idUnavailableTime, String description, LocalDateTime start, LocalDateTime finish) {
        this.idUnavailableTime = idUnavailableTime;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.active = true;
    }

    public UnavailableTime(UnavailableTimeRegisterDTO unavailableTimeRegisterDTO){
        this.description = unavailableTimeRegisterDTO.description();
        this.start = unavailableTimeRegisterDTO.start();
        this.finish = unavailableTimeRegisterDTO.finish();
        this.active = true;
    }



    public Long getIdUnavailableTime() {
        return idUnavailableTime;
    }

    public void setIdUnavailableTime(Long idUnavailableTime) {
        this.idUnavailableTime = idUnavailableTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnavailableTime that = (UnavailableTime) o;
        return Objects.equals(idUnavailableTime, that.idUnavailableTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnavailableTime);
    }

    @Override
    public String toString() {
        return "ScheduleException{" +
                "idScheduleException=" + idUnavailableTime +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", active=" + active +
                '}';
    }
}
