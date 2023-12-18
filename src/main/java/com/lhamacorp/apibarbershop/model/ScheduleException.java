package com.lhamacorp.apibarbershop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "ScheduleException")
@Table(name = "scheduleException")
public class ScheduleException {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScheduleException;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    private boolean active;

    public ScheduleException() {
    }

    public ScheduleException(Long idScheduleException, String description, LocalDateTime start, LocalDateTime finish) {
        this.idScheduleException = idScheduleException;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.active = true;
    }

    public Long getIdScheduleException() {
        return idScheduleException;
    }

    public void setIdScheduleException(Long idScheduleException) {
        this.idScheduleException = idScheduleException;
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
        ScheduleException that = (ScheduleException) o;
        return Objects.equals(idScheduleException, that.idScheduleException);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idScheduleException);
    }

    @Override
    public String toString() {
        return "ScheduleException{" +
                "idScheduleException=" + idScheduleException +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", active=" + active +
                '}';
    }
}
