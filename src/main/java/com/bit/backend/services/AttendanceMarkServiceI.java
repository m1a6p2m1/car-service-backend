package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.dtos.AttendanceMarkEmployeeDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.entities.EmployeeEntity;

import java.util.List;
import java.util.Map;

public interface AttendanceMarkServiceI {
//    AttendanceMarkDto saveAttendance(AttendanceMarkDto attendanceMarkDto);
//    List<AttendanceMarkEmployeeDto> getData();
    List<Map<String, Object>> getActiveEmployees();

    List<AttendanceMarkDto> saveAttendance(List<AttendanceMarkDto> attendanceList);

//    AttendanceMarkDto saveAttendance(List<AttendanceMarkDto> attendanceList);
}
