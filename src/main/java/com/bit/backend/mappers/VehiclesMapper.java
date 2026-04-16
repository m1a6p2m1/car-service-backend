package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.entities.VehiclesEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface VehiclesMapper {
    @Mapping(source = "user.id", target = "customerId")
    @Mapping(source = "user.uniqueCusNo", target = "uniqueCusNo")
    @Mapping(source = "user.firstName", target = "customerName")
    CustomerVehiclesDto toCustomerVehiclesDto(VehiclesEntity VehiclesEntity);
    VehiclesEntity toVehiclesEntity(CustomerVehiclesDto customerVehiclesDto);
    List<CustomerVehiclesDto> toCustomerVehiclesDtoList(List<VehiclesEntity> VehiclesEntityList);

}
