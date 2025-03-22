package com.bit.backend.mappers;

import com.bit.backend.dtos.SubTaskAssignDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.entities.SubTaskAssignedEntity;
import com.bit.backend.entities.TaskAssignEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TaskAssignMapper {
    @Mapping(source = "subTasks", target = "subTasks")
    TaskAssignDto toTaskAssignDto(TaskAssignEntity taskAssignEntity);

    @Mapping(source = "subTasks", target = "subTasks")
    TaskAssignEntity toTaskAssignEntity(TaskAssignDto taskAssignDto);

    SubTaskAssignedEntity toSubTaskAssignEntity(SubTaskAssignDto subTaskAssignDto);

    SubTaskAssignDto toSubTaskAssignDto(SubTaskAssignedEntity subTaskAssignedEntity);

    List<SubTaskAssignedEntity> toSubTaskAssignedEntityList(List<SubTaskAssignDto> subTasks);

    List<SubTaskAssignDto> toSubTaskAssignDto(List<SubTaskAssignedEntity> subTasks);

    List<TaskAssignDto> toTaskAssignDtoList(List<TaskAssignEntity> taskAssignEntityList);
}
