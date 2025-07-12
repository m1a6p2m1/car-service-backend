package com.bit.backend.repositories;

import com.bit.backend.entities.TaskAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TaskAssignRepository extends JpaRepository<TaskAssignEntity, Long> {
    List<TaskAssignEntity> findByCustomerId(Long customerId); // customer commonly used tasks loaded into the dashboard

    List<TaskAssignEntity> findByUniqueTaskNo(String parseLong);

    @Query("SELECT taskName as name, count(taskName) as cnt FROM TaskAssignEntity group by taskName")
    List<Map<String, Object>> getCommonTaskStats();
}
