package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceDTO;
import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceUpdateDTO;
import com.lhamacorp.apibarbershop.repository.ServiceRepository;
import com.lhamacorp.apibarbershop.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public URI registerService(ServiceRegisterDTO serviceRegisterData, UriComponentsBuilder uriBuilder){

         Service newService = new Service(serviceRegisterData);
         serviceRepository.save(newService);
         URI uri = uriBuilder.path("service/{id}").buildAndExpand(newService.getIdService()).toUri();

         return uri;
    }

    public List<ServiceDTO> getAllservices(){

        List<ServiceDTO> list = serviceRepository.findAllByActiveTrue();
        return list;
    }

    public ServiceUpdateDTO updateService(ServiceUpdateDTO serviceData){

        Service service = serviceRepository.getReferenceById(serviceData.idService());

        if(serviceData.name() != null){
            service.setName(serviceData.name());
        }
        if(serviceData.description() != null){
            service.setDescription(serviceData.description());
        }
        if(serviceData.duration() != null){
            service.setDuration(serviceData.duration());
        }
        if(serviceData.price() != null){
            service.setPrice(serviceData.price());
        }

        return new ServiceUpdateDTO(service);

    }

    public void deleteServiceById(Long id){

        Service service = serviceRepository.getReferenceById(id);

        service.setActive(false);
    }
}
