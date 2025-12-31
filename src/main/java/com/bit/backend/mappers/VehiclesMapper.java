package com.bit.backend.mappers;

import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.entities.VehiclesEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface VehiclesMapper {
    VehiclesDto toVehiclesDto(VehiclesEntity vehiclesEntity);
    VehiclesEntity toVehiclesEntity(VehiclesDto vehiclesDto);
    List<VehiclesDto> toVehiclesDtoList(List<VehiclesEntity> vehiclesEntityList);

}
