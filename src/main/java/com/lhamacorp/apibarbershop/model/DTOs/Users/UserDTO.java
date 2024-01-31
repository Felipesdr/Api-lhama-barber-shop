package com.lhamacorp.apibarbershop.model.DTOs.Users;

import com.lhamacorp.apibarbershop.model.User;

public record UserDTO(Long idUser, String name, String email, String phone) {

    public UserDTO(User user){
        this(user.getIdUser(), user.getName(), user.getEmail(), user.getPhone());
    }
}
