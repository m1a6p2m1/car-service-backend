package com.bit.backend.mappers;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AttendanceMarkMapper {
    AttendanceMarkDto toAttendanceMarkDto(AttendanceMarkEntity attendanceMarkEntity);
    AttendanceMarkEntity toAttendanceMarkEntity(AttendanceMarkDto attendanceMarkDto);
    List<AttendanceMarkDto> toAttendanceDtoList(List<AttendanceMarkEntity> attendanceMarkEntityList);
}
