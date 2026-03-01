package com.bit.backend.mappers;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.entities.EmployeeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AttendanceMarkMapper {
    default AttendanceMarkDto toAttendanceMarkDto(AttendanceMarkEntity entity) {
        AttendanceMarkDto dto = new AttendanceMarkDto();

        dto.setEmployeeId(entity.getEmployee().getEmpNumber());
        dto.setDate(entity.getDate());
        dto.setAttendanceStatus(entity.getAttendanceStatus());

        return dto;
    }
    default AttendanceMarkEntity toAttendanceMarkEntity(AttendanceMarkDto dto, EmployeeEntity employeeEntity) {
        AttendanceMarkEntity entity = new AttendanceMarkEntity();

        entity.setEmployee(employeeEntity);
        entity.setDate(dto.getDate());
        entity.setAttendanceStatus(dto.getAttendanceStatus());

        return entity;
    }
    List<AttendanceMarkDto> toAttendanceDtoList(List<AttendanceMarkEntity> attendanceMarkEntityList);
}
