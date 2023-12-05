package com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs;

import com.lhamacorp.apibarbershop.model.Barber;

public record BarberDTO(Long idBarber, String name, String phone, String description) {

    public BarberDTO(Barber barber){
        this(barber.getIdBarber(), barber.getName(), barber.getPhone(), barber.getDescription());
    }
}
