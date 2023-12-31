package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.repository.BarberRepository;
import com.lhamacorp.apibarbershop.repository.BarberUnavailableTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<BarberUnavailableTimeDTO> findAllFutureBarberUnavailableTimeByIdBarberAndActiveTrue(Long idBarber){

        List<BarberUnavailableTime> barberUnavailableTimeList = repository.findAllByBarberIdBarberAndActiveTrueAndStartAfter(idBarber, LocalDateTime.now());

        return barberUnavailableTimeList.stream().map(BarberUnavailableTimeDTO::new).collect(Collectors.toList());
    }

    public BarberUnavailableTimeDTO updateBarberUnavailableTime(BarberUnavailableTimeDTO updateData){

        BarberUnavailableTime update = repository.getReferenceById(updateData.idBarberUnavailableTime());

        if(updateData.description() != null){
            update.setDescription(updateData.description());
        }
        if(updateData.start() != null){
            update.setStart(updateData.start());
        }
        if(updateData.finish() != null){
            update.setFinish(updateData.finish());
        }

        repository.save(update);

        return new BarberUnavailableTimeDTO(update);
    }

    public void deleteBarberUnavailableTimeById(Long idBarberUnavailableTime){

        BarberUnavailableTime barberUnavailableTime = repository.getReferenceById(idBarberUnavailableTime);

        barberUnavailableTime.setActive(false);

        repository.save(barberUnavailableTime);
    }

}
