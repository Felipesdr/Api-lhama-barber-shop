package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.UnavailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UnavailableTimeRepository extends JpaRepository<UnavailableTime, Long> {

    List<UnavailableTime> findAllByActiveTrue();

    List<UnavailableTime> findAllByStartAfterOrStartEquals(LocalDateTime start, LocalDateTime startt);



}
