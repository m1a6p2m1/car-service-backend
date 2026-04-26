package com.bit.backend.repositories;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.dtos.AttendanceMarkEmployeeDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.entities.PrivilegeGroup;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceMarkRepository extends JpaRepository<AttendanceMarkEntity, Long> {

//    @Query(nativeQuery = true,
//            value = "select employee_id, full_name, status from ems.attendance_mark\n" +
//            "join ems.employee\n" +
//            "ON employee_id = id")
//    List<AttendanceMarkEmployeeDto> getEmployeeAttendanceDetails();

//    boolean existsByEmployee_EmpNumberAndDate(
//            Long empNumber,
//            LocalDate date
//    );
    List<AttendanceMarkEntity> findByDate(LocalDate date);
     Optional<AttendanceMarkEntity> findByEmployee_EmpNumberAndDate(
            Long empNumber,
            LocalDate date
    );
}
