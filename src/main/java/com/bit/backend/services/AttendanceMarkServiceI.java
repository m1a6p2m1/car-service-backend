package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.entities.AttendanceMarkEntity;

import java.util.List;

public interface AttendanceMarkServiceI {
    AttendanceMarkDto addAttendanceMarkEntity(AttendanceMarkDto attendanceMarkDto);
    List<AttendanceMarkDto> getData();
}
