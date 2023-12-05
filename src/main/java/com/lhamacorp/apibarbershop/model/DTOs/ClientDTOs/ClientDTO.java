package com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs;

import com.lhamacorp.apibarbershop.model.Client;

public record ClientDTO(Long idClient, String name, String email, String phone) {

    public ClientDTO(Client client){
        this(client.getIdClient(), client.getName(), client.getEmail(), client.getPhone());
    }
}


