package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserUpdateDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User registerUser(UserRegisterDTO registerUserData) {

        if(userRepository.existsByEmail(registerUserData.email())){

            return null;
        }

        User newUser = new User(registerUserData);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return newUser;
    }

    public Long registerUserClient(UserRegisterDTO registerUserData){

        User newUser = this.registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            return savedUser.getIdUser();
        } else {
            return null;
        }

    }

    public Long registerUserBarber(UserRegisterDTO registerUserData){

        User newUser = registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            savedUser.setRole(UserRole.BARBER);

            return savedUser.getIdUser();
        } else {
            return null;
        }
    }

    public UserUpdateDTO updateBarberOrClientById(UserUpdateDTO barberData){

        User user = userRepository.getReferenceById(barberData.idUser());

        if(user.getRole() != UserRole.ADMIN){

            if(barberData.name() != null){

                user.setName(barberData.name());
            }
            if(barberData.phone() != null){

                user.setPhone(barberData.phone());
            }
            if(barberData.email() != null){

                if(!userRepository.existsByEmail(barberData.email())){

                    user.setEmail(barberData.email());
                }else{
                    throw new RuntimeException("Email já cadastrado");
                }
            }
            if(barberData.password() != null){
                String hash = passwordEncoder.encode(barberData.password());

                user.setPassword(hash);
            }

        }

        UserUpdateDTO barberUpdatedData = new UserUpdateDTO(user);

        return barberUpdatedData;
    }

    public UserDTO findUserById(Long idBarber){

        var user = userRepository.findById(idBarber).get();

        return new UserDTO(user);
    }

    public List<UserDTO> findAllUsersByActiveTrueAndRole(UserRole role){

        List<User> list = userRepository.findAllByActiveTrueAndRole(role);

        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public void deleteBarberOrClient(Long idBarber){

        User barber = userRepository.getReferenceById(idBarber);
        barber.setActive(false);
    }
}
