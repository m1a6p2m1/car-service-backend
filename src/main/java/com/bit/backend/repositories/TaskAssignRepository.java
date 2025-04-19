package com.bit.backend.repositories;

import com.bit.backend.entities.TaskAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignRepository extends JpaRepository<TaskAssignEntity, Long> {
    List<TaskAssignEntity> findByCustomerId(Long customerId); // customer commonly used tasks loaded into the dashboard
}
