package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findAllByBarberIdUserAndStartAfterAndIdScheduleStatusNot(Long idUserBarber, LocalDateTime start, Integer idScheduleStatus);
}
