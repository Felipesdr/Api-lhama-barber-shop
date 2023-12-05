package com.lhamacorp.apibarbershop.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Service")
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
    private String name;
    private String description;
    private Integer duration;
    private Double price;
    private boolean active;

    public Service() {
    }

    public Service(Long idService, String name, String description, Integer duration, Double price) {
        this.idService = idService;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.active = true;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        Service service = (Service) o;
        return Objects.equals(idService, service.idService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idService);
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
