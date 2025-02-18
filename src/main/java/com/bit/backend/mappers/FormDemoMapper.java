package com.bit.backend.mappers;

import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.FormDemoEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FormDemoMapper {

    FormDemoDto toFormDemoDto(FormDemoEntity formDemoEntity);
    FormDemoEntity toFormDemoEntity(FormDemoDto formDemoDto);
    List<FormDemoDto> toFormDemoDtoList(List<FormDemoEntity> formDemoEntityList);
}
