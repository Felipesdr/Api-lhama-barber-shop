package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.infra.security.TokenService;
import com.lhamacorp.apibarbershop.model.BarberUnavailableTime;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeDTO;
import com.lhamacorp.apibarbershop.model.DTOs.BarberUnavailableTimeDTOs.BarberUnavailableTimeRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.repository.BarberUnavailableTimeRepository;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberUnavaibleTimeService {
    @Autowired
    private BarberUnavailableTimeRepository barberUnavailableTimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    public Long registerBarberUnavaibleTime(BarberUnavailableTimeRegisterDTO unavaibleTimeData, HttpHeaders headers){

        Long idUser = tokenService.getIdFromToken(headers);

        User requestingUser = userRepository.getReferenceById(idUser);

        User barber = userRepository.getReferenceById(unavaibleTimeData.idBarber());

        userIdValidationForNonAdmin(idUser, barber.getIdUser(), requestingUser);

        isBarberValidation(barber);

        UserDTO userDTO = new UserDTO(userRepository.findById(unavaibleTimeData.idBarber()).get());

        User UnavailableBarber = new User(userDTO);

        BarberUnavailableTime barberUnavaibleTime = new BarberUnavailableTime(unavaibleTimeData, UnavailableBarber);

        return barberUnavailableTimeRepository.save(barberUnavaibleTime).getIdBarberUnavailableTime();
    }

    public List<BarberUnavailableTimeDTO> findAllFutureBarberUnavailableTimeByIdBarberAndActiveTrue(Long idBarber, HttpHeaders headers){

        Long idUser = tokenService.getIdFromToken(headers);

        User requestingUser = userRepository.getReferenceById(idUser);

        User barber = userRepository.getReferenceById(idBarber);

        userIdValidationForNonAdmin(idBarber, idUser, requestingUser);

        isBarberValidation(barber);

        List<BarberUnavailableTime> barberUnavailableTimeList = barberUnavailableTimeRepository.findAllByBarberIdUserAndActiveTrueAndStartAfter(idBarber, LocalDateTime.now());

        return barberUnavailableTimeList.stream().map(BarberUnavailableTimeDTO::new).collect(Collectors.toList());
    }

    public BarberUnavailableTimeDTO updateBarberUnavailableTime(BarberUnavailableTimeDTO updateData){

        BarberUnavailableTime update = barberUnavailableTimeRepository.getReferenceById(updateData.idBarberUnavailableTime());

        if(updateData.description() != null){
            update.setDescription(updateData.description());
        }
        if(updateData.start() != null){
            update.setStart(updateData.start());
        }
        if(updateData.finish() != null){
            update.setFinish(updateData.finish());
        }

        barberUnavailableTimeRepository.save(update);

        return new BarberUnavailableTimeDTO(update);
    }

    public void deleteBarberUnavailableTimeById(Long idBarberUnavailableTime){

        BarberUnavailableTime barberUnavailableTime = barberUnavailableTimeRepository.getReferenceById(idBarberUnavailableTime);

        barberUnavailableTime.setActive(false);

        barberUnavailableTimeRepository.save(barberUnavailableTime);
    }

    private void userIdValidationForNonAdmin(Long idUser, Long idParameter, User requestingUser){

        if(requestingUser.getRole() != UserRole.ADMIN && idUser != idParameter){

            throw new RuntimeException("Você não pode fazer isso!");

        }

    }

    private void isBarberValidation(User user){

        if(user.getRole() != UserRole.BARBER){

            throw new RuntimeException("Usuario fornecido não é um barbeiro cadastrado");
        }
    }

}
