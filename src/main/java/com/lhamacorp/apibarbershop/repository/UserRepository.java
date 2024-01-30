package com.lhamacorp.apibarbershop.repository;


import com.lhamacorp.apibarbershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}