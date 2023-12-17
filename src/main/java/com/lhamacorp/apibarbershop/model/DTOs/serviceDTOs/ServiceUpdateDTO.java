package com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs;

import com.lhamacorp.apibarbershop.model.Service;

public record ServiceUpdateDTO(Long idService, String name, String description, Integer duration, Double price) {

    public ServiceUpdateDTO(Service service){
        this(service.getIdService(), service.getName(), service.getDescription(), service.getDuration(), service.getPrice());
    }
}
