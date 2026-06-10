package com.bit.backend.repositories;

import com.bit.backend.entities.SubTaskAssignedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTasksAssignRepository extends JpaRepository<SubTaskAssignedEntity, Long> {
    List<SubTaskAssignedEntity> findByAssignedUserId(Long empId);
    List<SubTaskAssignedEntity> findBySupervisor(Long empId);
    List<SubTaskAssignedEntity> findByMainUniqueTaskNo(String mainUniqueTaskNo);
}
