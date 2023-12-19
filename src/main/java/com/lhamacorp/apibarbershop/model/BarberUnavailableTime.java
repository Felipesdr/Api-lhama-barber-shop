package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeRegisterDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "BarberUnavableTime")
@Table(name = "barberUnavailableTime")
public class BarberUnavailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarberUnavailableTime;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    @ManyToOne
    @JoinColumn(name = "idBarber", nullable = false)
    private Barber barber;
    private boolean active;
    public BarberUnavailableTime() {
    }

    public BarberUnavailableTime(Long idBarberUnavailableTime, String description, LocalDateTime start, LocalDateTime end, Barber barber) {
        this.idBarberUnavailableTime = idBarberUnavailableTime;
        this.description = description;
        this.start = start;
        this.finish = end;
        this.barber = barber;
        this.active = true;
    }

    public BarberUnavailableTime(BarberUnavailableTimeRegisterDTO barberUnavailableTimeRegisterDTO, BarberDTO barberDTO){
        this.description = barberUnavailableTimeRegisterDTO.description();
        this.start = barberUnavailableTimeRegisterDTO.start();
        this.finish = barberUnavailableTimeRegisterDTO.end();
        this.barber = new Barber(barberDTO);
        this.active = true;
    }

    public Long getIdBarberUnavailableTime() {
        return idBarberUnavailableTime;
    }

    public void setIdBarberUnavailableTime(Long idBarberUnavailableTime) {
        this.idBarberUnavailableTime = idBarberUnavailableTime;
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

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
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
        BarberUnavailableTime that = (BarberUnavailableTime) o;
        return Objects.equals(idBarberUnavailableTime, that.idBarberUnavailableTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBarberUnavailableTime);
    }

    @Override
    public String toString() {
        return "BarberUnavailableTime{" +
                "idBarberUnavailableTime=" + idBarberUnavailableTime +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + finish +
                ", barber=" + barber +
                '}';
    }
}
