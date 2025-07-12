package com.bit.backend.mappers;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.entities.AppointmentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AppointmentMapper {

    AppointmentDto toAppointmentDto(AppointmentEntity appointmentEntity);
    AppointmentEntity toAppointmentEntity(AppointmentDto appointmentDto);
    List<AppointmentDto> toAppointmentDtoList(List<AppointmentEntity> appointmentEntities);
}
