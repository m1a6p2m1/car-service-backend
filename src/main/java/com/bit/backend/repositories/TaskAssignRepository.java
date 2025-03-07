package com.bit.backend.repositories;

import com.bit.backend.entities.TaskAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignRepository extends JpaRepository<TaskAssignEntity, Long> {
}
