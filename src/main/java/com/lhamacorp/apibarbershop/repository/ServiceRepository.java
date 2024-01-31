package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceDTO;
import com.lhamacorp.apibarbershop.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<ServiceDTO> findAllByActiveTrue();
}
