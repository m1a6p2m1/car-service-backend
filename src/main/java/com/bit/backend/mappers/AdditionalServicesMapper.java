package com.bit.backend.mappers;

import com.bit.backend.dtos.AdditionalServicesDto;
import com.bit.backend.entities.AdditionalServicesEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AdditionalServicesMapper {
    AdditionalServicesDto toDto(AdditionalServicesEntity additionalServicesEntity);
    AdditionalServicesEntity toEntity(AdditionalServicesDto additionalServicesDto);
    List<AdditionalServicesDto> toDtoList(List<AdditionalServicesEntity> additionalServicesEntityList);
}
