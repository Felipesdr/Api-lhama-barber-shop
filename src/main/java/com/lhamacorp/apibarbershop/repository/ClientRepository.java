package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.Client;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientDTO;
import com.lhamacorp.apibarbershop.model.DTOs.ClientDTOs.ClientRegisterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<ClientDTO> findAllByActiveTrue();
}


