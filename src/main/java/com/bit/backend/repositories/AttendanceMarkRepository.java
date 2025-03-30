package com.bit.backend.repositories;

import com.bit.backend.dtos.AttendanceMarkEmployeeDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.entities.PrivilegeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceMarkRepository extends JpaRepository<AttendanceMarkEntity, Long> {

    @Query(nativeQuery = true,
            value = "select employee_id, full_name, status from ems.attendance_mark\n" +
            "join ems.employee\n" +
            "ON employee_id = id")
    List<AttendanceMarkEmployeeDto> getEmployeeAttendanceDetails();
}
