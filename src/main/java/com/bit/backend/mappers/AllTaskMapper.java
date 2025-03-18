package com.bit.backend.mappers;

import com.bit.backend.dtos.AllTaskDto;
import com.bit.backend.entities.AllTaskEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AllTaskMapper {

    AllTaskDto toAllTaskDto(AllTaskEntity allTaskEntity);
    AllTaskEntity toAllTAskEntity(AllTaskDto allTaskDto);
    List<AllTaskDto> toAllTaskDtoList(List<AllTaskEntity> allTaskEntityList);
}
