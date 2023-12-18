package com.lhamacorp.apibarbershop.repository;


import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberUnavailableTimeRepository extends JpaRepository<BarberUnavailableTime, Long> {
}
