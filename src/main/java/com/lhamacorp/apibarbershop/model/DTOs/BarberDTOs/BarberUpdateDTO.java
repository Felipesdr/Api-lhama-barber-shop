package com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs;

import com.lhamacorp.apibarbershop.model.Barber;

public record BarberUpdateDTO(Long idBarber, String name, String phone, String description) {

    public BarberUpdateDTO(Barber barber){
        this(barber.getIdBarber(), barber.getName(), barber.getPhone(), barber.getDescription());
    }
}
