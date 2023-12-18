package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTime.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.repository.BarberRepository;
import com.lhamacorp.apibarbershop.repository.BarberUnavailableTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class BarberUnavaibleTimeService {
    @Autowired
    private BarberUnavailableTimeRepository repository;
    @Autowired
    private BarberRepository barberRepository;
    public URI registerBarberUnavaibleTime(BarberUnavailableTimeRegisterDTO unavaibleTimeData, UriComponentsBuilder uriBuilder){

        BarberDTO barberDTO = new BarberDTO(barberRepository.findById(unavaibleTimeData.idBarber()).get());

        BarberUnavailableTime barberUnavaibleTime = new BarberUnavailableTime(unavaibleTimeData,barberDTO);

        repository.save(barberUnavaibleTime);

        URI uri = uriBuilder.path("/barberUnavaibleTime/{id}").buildAndExpand(barberUnavaibleTime.getIdBarberUnavailableTime()).toUri();

        return uri;
    }
}
