package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT empNumber as id, fullName as name, jobTitle as position, employeeStatus as empStatus FROM EmployeeEntity ")
    List<Map<String, Object>> getEmployeeList();

    @Query("SELECT jobTitle as title, count(jobTitle) as cnt FROM EmployeeEntity group by jobTitle")
    List<Map<String, Object>> getEmployeeCountByJobRole();

    @Query("SELECT empNumber as id, uniqueEmpNo as uniqueEmpNo, fullName as name, employeeStatus as empStatus FROM EmployeeEntity WHERE employeeStatus = 'Active'")
    List<Map<String, Object>> getActiveEmployeesList();

    Optional<EmployeeEntity> findByEmpNumber(long empNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNic(String nic);
}
//    List<EmployeeEntity> findByEmployeeStatus(String employeeStatus);

