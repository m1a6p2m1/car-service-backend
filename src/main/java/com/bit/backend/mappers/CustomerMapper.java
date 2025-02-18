package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.entities.CustomerEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomerMapper {
    CustomerDto toCustomerDto(CustomerEntity customerEntity);
    CustomerEntity toCustomerEntity(CustomerDto customerDto);
    List<CustomerDto> toCustomerDtoList(List<CustomerEntity> customerEntityList);
}
