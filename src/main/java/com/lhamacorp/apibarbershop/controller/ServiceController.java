package com.lhamacorp.apibarbershop.controller;

import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.serviceDTOs.ServiceUpdateDTO;
import com.lhamacorp.apibarbershop.service.ServiceService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerService(@RequestBody @Valid ServiceRegisterDTO request, UriComponentsBuilder uriBuilder){
        System.out.println(request);

        URI uri = serviceService.registerService(request, uriBuilder);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/services")
    public ResponseEntity getAllServices(){

        return ResponseEntity.ok(serviceService.getAllservices());
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity updateService(@RequestBody ServiceUpdateDTO request){

        ServiceUpdateDTO serviceUpdateDTO = serviceService.updateService(request);

        return ResponseEntity.ok(serviceUpdateDTO);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity deleteServiceById(@PathVariable Long id){

        serviceService.deleteServiceById(id);

        return ResponseEntity.noContent().build();
    }

}
