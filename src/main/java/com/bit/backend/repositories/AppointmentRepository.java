package com.bit.backend.repositories;

import com.bit.backend.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("""
           SELECT a.timeSlot, COUNT(a.id)
           FROM AppointmentEntity a
           WHERE a.appointmentDate = :day
           GROUP BY a.timeSlot
           """)
    List<Object[]> countPerTimeSlot(@Param("day") LocalDate day);

    /** Check if a bay is free at a slot */
    Optional<AppointmentEntity> findByAppointmentDateAndTimeSlotAndBay(
            LocalDate appointmentDate, LocalTime timeSlot, Integer bay);
}
