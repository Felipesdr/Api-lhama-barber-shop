package com.lhamacorp.apibarbershop.repository;


import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTime.BarberUnavailableTimeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberUnavailableTimeRepository extends JpaRepository<BarberUnavailableTime, Long> {
    List<BarberUnavailableTimeDTO> findAllByBarberIdBarberAndActiveTrue(Long idBarber);
}
