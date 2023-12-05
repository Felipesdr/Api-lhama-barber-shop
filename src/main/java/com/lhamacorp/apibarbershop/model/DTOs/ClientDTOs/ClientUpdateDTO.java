package com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs;

import com.lhamacorp.apibarbershop.model.Client;

public record ClientUpdateDTO(Long idClient, String name, String phone) {

    public ClientUpdateDTO(Client client){
        this(client.getIdClient(), client.getName(), client.getPhone());
    }
}


