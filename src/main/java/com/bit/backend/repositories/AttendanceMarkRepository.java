package com.bit.backend.repositories;

import com.bit.backend.entities.AttendanceMarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceMarkRepository extends JpaRepository<AttendanceMarkEntity, Long> {
}
