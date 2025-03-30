package com.bit.backend.mappers;

import com.bit.backend.dtos.DefinedSubTaskDto;
import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.entities.DefinedSubTaskEntity;
import com.bit.backend.entities.DefinedTasksEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DefinedTasksMapper {
    @Mapping(source = "definedSubTaskEntities", target = "definedSubTaskDtos")
    DefinedTasksDto toDefinedTasksDto(DefinedTasksEntity definedTasksEntity);

    @Mapping(source = "definedSubTaskDtos", target = "definedSubTaskEntities")
    DefinedTasksEntity toDefinedTasksEntity(DefinedTasksDto definedTasksDto);

    DefinedSubTaskDto toDefinedSubTasksDto(DefinedSubTaskEntity definedSubTaskEntity);

    List<DefinedSubTaskEntity> toDefinedSubTaskEntityList(List<DefinedSubTaskDto> definedSubTaskDtos);
    List<DefinedSubTaskDto> toDefinedSubTaskDto(List<DefinedSubTaskEntity> definedSubTaskEntities);

    List<DefinedTasksDto> toDefinedTasksDtos(List<DefinedTasksEntity> definedTasksEntities);
    List<DefinedTasksEntity> toDefinedTasksEntityList(List<DefinedTasksDto> definedTasksDtos);

    @AfterMapping
    default void linkSubTasks(@MappingTarget DefinedTasksEntity definedTasksEntity) {
        if (definedTasksEntity.getDefinedSubTaskEntities() != null) {
            definedTasksEntity.getDefinedSubTaskEntities().forEach(subTask -> subTask.setDefinedTasksEntity(definedTasksEntity));
        }
    }
}
