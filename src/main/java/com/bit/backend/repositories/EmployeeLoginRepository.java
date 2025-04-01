package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLoginEntity, Long> {
}
