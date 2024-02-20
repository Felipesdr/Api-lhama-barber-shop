package com.lhamacorp.apibarbershop.repository;


import com.lhamacorp.apibarbershop.model.User;
import com.lhamacorp.apibarbershop.model.ENUMs.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByActiveTrueAndRole(UserRole role);

    User findByNameAndRole(String name, UserRole role);

    User findByEmailAndRole(String email, UserRole role);
}
