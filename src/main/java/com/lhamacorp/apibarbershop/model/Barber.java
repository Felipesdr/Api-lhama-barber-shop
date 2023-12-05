package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberUpdateDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Barber")
@Table(name = "barber")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarber;
    private String name;
    private String phone;
    private String description;
    private boolean active;

    public Barber() {
    }
    public Barber(Long idBarber, String name, String phone, String description, boolean active) {
        this.idBarber = idBarber;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.active = active;
    }

    public Barber(BarberRegisterDTO barberRegisterDTO){
        this.name = barberRegisterDTO.name();
        this.phone = barberRegisterDTO.phone();
        this.description = barberRegisterDTO.description();
        this.active = true;
    }

    public Barber(BarberUpdateDTO barberUpdateDTO){
        this.idBarber = barberUpdateDTO.idBarber();
        this.name = barberUpdateDTO.name();
        this.phone = barberUpdateDTO.phone();
        this.description = barberUpdateDTO.description();
        this.active = true;
    }

    public Long getIdBarber() {
        return idBarber;
    }

    public void setIdBarber(Long idBarber) {
        this.idBarber = idBarber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Barber barber = (Barber) o;
        return Objects.equals(idBarber, barber.idBarber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBarber);
    }

    @Override
    public String toString() {
        return "Barber{" +
                "idBarber=" + idBarber +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
