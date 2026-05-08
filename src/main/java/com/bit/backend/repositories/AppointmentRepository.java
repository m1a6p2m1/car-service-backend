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

    long countByDateAndTime(LocalDate appointmentDate, LocalTime timeSlot);

    @Query("""
           SELECT a.time, COUNT(a.id)
           FROM AppointmentEntity a
           WHERE a.date = :day
           GROUP BY a.time
           """)
    List<Object[]> countPerTimeSlot(@Param("day") LocalDate day);

    /** Check if a bay is free at a slot */
    Optional<AppointmentEntity> findByDateAndTimeAndBay(
            LocalDate appointmentDate, LocalTime timeSlot, Integer bay);

    List<AppointmentEntity> findByUser_UniqueCusNo(String uniqueCusNo);

//   get Appointment numbers related to the date and time(taskAssign)
    @Query("SELECT a FROM AppointmentEntity a WHERE a.date = :date AND a.time = :time AND (a.status IS NULL OR a.status != 'ASSIGNED_TO_TASK')")
    List<AppointmentEntity> findByDateAndTime(
            @Param("date") LocalDate date,
            @Param("time") LocalTime time);

    //get Appointments details when select the appointment no(taskAssign)
    @Query("SELECT a FROM AppointmentEntity a WHERE a.appointmentUniqueNo = :no")
    Optional<AppointmentEntity> findByAppointment_UniqueNo(@Param("no") String no);
}
