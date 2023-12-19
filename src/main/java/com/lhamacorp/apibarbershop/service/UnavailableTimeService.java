package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.UnavailableTimeDTOs.UnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.model.UnavailableTime;
import com.lhamacorp.apibarbershop.repository.UnavailableTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UnavailableTimeService {
    @Autowired
    private UnavailableTimeRepository repository;

    public URI registerUnavailableTime(UnavailableTimeRegisterDTO unavailableTimeRegisterData, UriComponentsBuilder uriBuilder){

        UnavailableTime unavailableTime = new UnavailableTime(unavailableTimeRegisterData);

        repository.save(unavailableTime);

        URI uri = uriBuilder.path("/unavailableTime/{idUnavailableTime}").buildAndExpand(unavailableTime.getIdUnavailableTime()).toUri();

        return uri;
    }

    public List<UnavailableTimeDTO> findAllUnavailableTime(){

        return repository.findAllByActiveTrue();
    }

    public List<UnavailableTimeDTO> findUnavailableTimesBetweenGap(LocalDateTime start, LocalDateTime finish){

        List<UnavailableTimeDTO> allUnavailableTime = repository.findAllByActiveTrue();

        List<UnavailableTimeDTO> unavailableTimesBetweenGap = allUnavailableTime.stream()
                .filter(un -> un.start().isAfter(start) && un.start().isBefore(finish) )
                .toList();

        return unavailableTimesBetweenGap;
    }

    public UnavailableTimeDTO updateUnavailableTime(UnavailableTimeDTO unavailableTimeDTOupdateData){

        UnavailableTime unavailableTimeUpdated = repository.getReferenceById(unavailableTimeDTOupdateData.idUnavailableTime());

        if(unavailableTimeDTOupdateData.description() != null){
            unavailableTimeUpdated.setDescription(unavailableTimeDTOupdateData.description());
        }
        if(unavailableTimeDTOupdateData.start() != null){
            unavailableTimeUpdated.setStart(unavailableTimeDTOupdateData.start());
        }
        if(unavailableTimeDTOupdateData.finish() != null){
            unavailableTimeUpdated.setFinish(unavailableTimeDTOupdateData.finish());
        }

        return new UnavailableTimeDTO(unavailableTimeUpdated);
    }

    public void deleteUnavailableTimeById(Long idUnavailableTime){

        UnavailableTime deletedUnavailableTime = repository.getReferenceById(idUnavailableTime);

        deletedUnavailableTime.setActive(false);

    }
}
