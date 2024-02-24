package com.lhamacorp.apibarbershop.repository;

import com.lhamacorp.apibarbershop.model.ENUMs.ScheduleStatus;
import com.lhamacorp.apibarbershop.model.Schedule;
import com.lhamacorp.apibarbershop.model.UnavailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findAllByBarberIdUserAndStartAfterAndStatusNot(Long idUserBarber, LocalDateTime start, ScheduleStatus status);

    List<Schedule> findAllByStartAfterAndFinishBeforeAndStatusNot(LocalDateTime start, LocalDateTime finish, ScheduleStatus status);

    List<Schedule> findAllByStartAfterOrStartEqualsAndStartBeforeAndStatusNot(LocalDateTime start, LocalDateTime startt, LocalDateTime finish, ScheduleStatus status);
}
