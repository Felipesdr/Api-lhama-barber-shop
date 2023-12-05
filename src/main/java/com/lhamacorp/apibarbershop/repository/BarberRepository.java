package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.Barber;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {

    List<BarberDTO> findAllByActiveTrue();
}
