package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientRegisterDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Client")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String name;
    private String email;
    private String phone;
    private boolean active;

    public Client() {
    }

    public Client(ClientRegisterDTO clientRegisterDTO) {
        this.name = clientRegisterDTO.name();
        this.phone = clientRegisterDTO.phone();
        this.email = clientRegisterDTO.email();
        this.active = true;
    }

    public Client(Long idClient, String name, String email, String phone, boolean active) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active= active;
    }

    public Long getIdClient() {
        return idClient;
    }
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
