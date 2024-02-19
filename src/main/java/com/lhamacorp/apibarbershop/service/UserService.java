package com.lhamacorp.apibarbershop.service;

import com.lhamacorp.apibarbershop.infra.security.TokenService;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserUpdateDTO;
import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.model.UserRole;
import com.lhamacorp.apibarbershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Long registerUserClient(UserRegisterDTO registerUserData) {

        User newUser = this.registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            return savedUser.getIdUser();
        } else {
            return null;
        }

    }

    public Long registerUserBarber(UserRegisterDTO registerUserData) {

        User newUser = registerUser(registerUserData);

        if (newUser != null) {
            User savedUser = userRepository.save(newUser);

            savedUser.setRole(UserRole.BARBER);

            return savedUser.getIdUser();
        } else {
            return null;
        }
    }

    private User registerUser(UserRegisterDTO registerUserData) {

        if (userRepository.existsByEmail(registerUserData.email())) {

            return null;
        }

        User newUser = new User(registerUserData);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return newUser;
    }


    public UserUpdateDTO updateUserOwnAccount(UserUpdateDTO userData, HttpHeaders header) {

        Long tokenId = tokenService.getIdFromToken(header);

        if(userData.idUser() != tokenId){

            return null;
        }

        return updateUser(userData);
    }

    //For ADMIN edit other accounts
    public UserUpdateDTO updateUser(UserUpdateDTO userData) {

        User user = userRepository.getReferenceById(userData.idUser());

        if (userData.name() != null) {

            user.setName(userData.name());
        }
        if (userData.phone() != null) {

            user.setPhone(userData.phone());
        }
        if (userData.email() != null) {

            if (!userRepository.existsByEmail(userData.email())) {

                user.setEmail(userData.email());
            } else {
                throw new RuntimeException("Email já cadastrado");
            }
        }
        if (userData.password() != null) {
            String hash = passwordEncoder.encode(userData.password());

            user.setPassword(hash);
        }


        UserUpdateDTO barberUpdatedData = new UserUpdateDTO(user);

        return barberUpdatedData;
    }

    public UserDTO findUserById(Long idBarber) {

        User user = userRepository.findById(idBarber).get();

        return new UserDTO(user);
    }

    public UserDTO findUserByName(String name, UserRole role){

        User user = userRepository.findByNameAndRole(name, role);

        return new UserDTO(user);
    }

    public UserDTO findUserByEmail(String email, UserRole role){

        User user = userRepository.findByEmailAndRole(email, role);

        return new UserDTO(user);
    }

    public List<UserDTO> findAllUsersByActiveTrueAndRole(UserRole role) {

        List<User> list = userRepository.findAllByActiveTrueAndRole(role);

        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public void deleteBarberOrClient(Long idUser) {

        User user = userRepository.getReferenceById(idUser);

        if(user.getRole() != UserRole.ADMIN){

            user.setActive(false);

        }else {

            throw new RuntimeException("Não é permitido deletar usuário administrador");

        }
    }
}
