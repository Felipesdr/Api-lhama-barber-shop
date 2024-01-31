package com.lhamacorp.apibarbershop.model.DTOs.Users;

import com.lhamacorp.apibarbershop.model.User;

public record UserUpdateDTO(Long idUser, String name, String email, String phone, String password) {

    public UserUpdateDTO(User user){
        this(user.getIdUser(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword());
    }
}
