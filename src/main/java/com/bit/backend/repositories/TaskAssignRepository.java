package com.bit.backend.repositories;

import com.bit.backend.entities.AppointmentEntity;
import com.bit.backend.entities.CustomerFeedbackEntity;
import com.bit.backend.entities.TaskAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskAssignRepository extends JpaRepository<TaskAssignEntity, Long> {
    List<TaskAssignEntity> findByCustomerId(Long customerId); // customer commonly used tasks loaded into the dashboard

    List<TaskAssignEntity> findByUniqueTaskNo(String parseLong);

    //get tasks that assign to the supervisor into the task tracker
    List<TaskAssignEntity> findBySupervisor(Long supervisor);
    List<TaskAssignEntity> findBySupervisorAndUniqueTaskNo(Long supervisor, String taskNo);


    @Query("SELECT taskName as name, count(taskName) as cnt FROM TaskAssignEntity group by taskName")
    List<Map<String, Object>> getCommonTaskStats();

    @Query("""
        SELECT t
        FROM TaskAssignEntity t
        WHERE t.date = :date
        AND t.customerId = :customerId
    """)
    List<TaskAssignEntity> findLicenseByDateAndCustomer(
            @Param("date") LocalDate date,
            @Param("customerId") Long customerId);

    //get Appointments details when select the license plate no for customer feedback
    @Query("""
            SELECT t
            FROM TaskAssignEntity t
            WHERE t.date = :date
            AND t.licencePlate = :licencePlate
            """)
    Optional<TaskAssignEntity> findByLicensePlate(
            @Param("date") LocalDate date,
            @Param("licencePlate") String licencePlate);
}
