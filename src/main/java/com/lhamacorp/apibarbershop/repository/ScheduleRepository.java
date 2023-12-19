package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
