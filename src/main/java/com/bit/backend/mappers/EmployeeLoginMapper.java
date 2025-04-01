package com.bit.backend.mappers;

import com.bit.backend.dtos.EmployeeLoginDto;
import com.bit.backend.entities.EmployeeLoginEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface EmployeeLoginMapper {
    EmployeeLoginDto toEmployeeLoginDto(EmployeeLoginEntity employeeLoginEntity);
    EmployeeLoginEntity toEmployeeLoginEntity(EmployeeLoginDto employeeLoginDto);
    List<EmployeeLoginDto> toEmployeeLoginDtoList(List<EmployeeLoginEntity> employeeLoginEntityList);
}
