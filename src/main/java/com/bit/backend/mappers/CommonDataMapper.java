package com.bit.backend.mappers;

import com.bit.backend.dtos.CommonDataDto;
import com.bit.backend.entities.CommonDataEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommonDataMapper {
    CommonDataEntity toCommonDataEntity(CommonDataDto commonDataDto);
    List<CommonDataEntity> toCommonDataEntityList(List<CommonDataDto> commonDataDtos);
    List<CommonDataDto> toCommonDataDtoList(List<CommonDataEntity> commonDataEntities);
}
