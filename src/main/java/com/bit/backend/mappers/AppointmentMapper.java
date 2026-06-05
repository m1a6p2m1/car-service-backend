package com.bit.backend.mappers;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.entities.AppointmentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AppointmentMapper {

    @Mapping(source = "definedTasks.id", target = "taskId")
    @Mapping(source = "definedTasks.taskName", target = "taskName")
    AppointmentDto toAppointmentDto(AppointmentEntity appointmentEntity);

    @Mapping(target = "definedTasks", ignore = true)
    AppointmentEntity toAppointmentEntity(AppointmentDto appointmentDto);
    List<AppointmentDto> toAppointmentDtoList(List<AppointmentEntity> appointmentEntities);
}
