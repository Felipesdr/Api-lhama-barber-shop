package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.repository.BarberUnavailableTimeRepository;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberUnavaibleTimeService {
    @Autowired
    private BarberUnavailableTimeRepository repository;
    @Autowired
    private UserRepository userRepository;
    public Long registerBarberUnavaibleTime(BarberUnavailableTimeRegisterDTO unavaibleTimeData){

        UserDTO userDTO = new UserDTO(userRepository.findById(unavaibleTimeData.idBarber()).get());

        User barber = new User(userDTO);

        BarberUnavailableTime barberUnavaibleTime = new BarberUnavailableTime(unavaibleTimeData, barber);

        return repository.save(barberUnavaibleTime).getIdBarberUnavailableTime();
    }

    public List<BarberUnavailableTimeDTO> findAllFutureBarberUnavailableTimeByIdBarberAndActiveTrue(Long idBarber){

        List<BarberUnavailableTime> barberUnavailableTimeList = repository.findAllByBarberIdUserAndActiveTrueAndStartAfter(idBarber, LocalDateTime.now());

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
