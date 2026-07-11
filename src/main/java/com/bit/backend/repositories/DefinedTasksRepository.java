package com.bit.backend.repositories;

import com.bit.backend.dtos.TaskIntroduceDto;
import com.bit.backend.entities.DefinedTasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DefinedTasksRepository extends JpaRepository<DefinedTasksEntity, Long> {
    // Option 1: Fetch entire Task + SubTasks as entity
//    @Query("SELECT DISTINCT t FROM DefinedTasksEntity t LEFT JOIN FETCH t.definedSubTaskEntities")
//    List<DefinedTasksEntity> findAllWithSubTasks();
    Optional<DefinedTasksEntity> findByTaskName(String taskName);
}
