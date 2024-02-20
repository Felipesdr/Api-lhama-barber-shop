package com.lhamacorp.apibarbershop.model.ENUMs;

public enum UserRole {
    ADMIN("admin"),
    BARBER("barber"),
    CLIENT("client");

    private String role;
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
