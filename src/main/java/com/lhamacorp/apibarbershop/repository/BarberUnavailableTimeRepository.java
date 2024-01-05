package com.lhamacorp.apibarbershop.repository;


import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BarberUnavailableTimeRepository extends JpaRepository<BarberUnavailableTime, Long> {
    List<BarberUnavailableTime> findAllByBarberIdBarberAndActiveTrueAndStartAfter(Long idBarber, LocalDateTime now);
}
