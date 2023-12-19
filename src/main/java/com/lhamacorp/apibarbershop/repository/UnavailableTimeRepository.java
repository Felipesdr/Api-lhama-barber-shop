package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.UnavailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavailableTimeRepository extends JpaRepository<UnavailableTime, Long> {

    List<UnavailableTimeDTO> findAllByActiveTrue();

}
