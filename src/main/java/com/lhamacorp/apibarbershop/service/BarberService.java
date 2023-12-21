package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.Barber;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberDTOs.BarberUpdateDTO;
import com.lhamacorp.apibarbershop.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberService {
    @Autowired
    private BarberRepository barberRepository;
    public List<BarberDTO> findAllBarbersByActiveTrue(){

        List<Barber> list = barberRepository.findAllByActiveTrue();

        return list.stream().map(BarberDTO::new).collect(Collectors.toList());

    }

    public BarberDTO findBarberById(Long idBarber){

        var barber = barberRepository.findById(idBarber).get();

        return new BarberDTO(barber);
    }
    public URI registerBarber(BarberRegisterDTO barberData, UriComponentsBuilder uribuilder){

        Barber barber = new Barber(barberData);
        barberRepository.save(barber);
        URI uri = uribuilder.path("/barber/{id}").buildAndExpand(barber.getIdBarber()).toUri();
        return uri;
    }
    public BarberUpdateDTO updateBarber(BarberUpdateDTO barberData){

        Barber barber = barberRepository.getReferenceById(barberData.idBarber());
        if(barberData.name() != null){
            barber.setName(barberData.name());
        }
        if(barberData.phone() != null){
            barber.setPhone(barberData.phone());
        }
        if(barberData.description() != null){
            barber.setDescription(barberData.description());
        }

        BarberUpdateDTO barberUpdatedData = new BarberUpdateDTO(barber);

        return barberUpdatedData;
    }

    public void deleteBarber(Long idBarber){

        Barber barber = barberRepository.getReferenceById(idBarber);
        barber.setActive(false);
    }
}
