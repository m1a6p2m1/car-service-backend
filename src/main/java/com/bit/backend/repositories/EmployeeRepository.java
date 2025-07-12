package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT empNumber as id, fullName as name, jobTitle as position FROM EmployeeEntity ")
    List<Map<String, Object>> getEmployeeList();

    @Query("SELECT jobTitle as title, count(jobTitle) as cnt FROM EmployeeEntity group by jobTitle")
    List<Map<String, Object>> getEmployeeCountByJobRole();
}
