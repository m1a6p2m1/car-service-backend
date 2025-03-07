package com.bit.backend.mappers;

import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.entities.TaskAssignEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TaskAssignMapper {
    TaskAssignDto toTaskAssignDto(TaskAssignEntity taskAssignEntity);
    TaskAssignEntity toTaskAssignEntity(TaskAssignDto taskAssignDto);
    List<TaskAssignDto> toTaskAssignDtoList(List<TaskAssignEntity> taskAssignEntityList);
}
