package com.bit.backend.mappers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity);
    EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto);

    List<EmployeeDto> toEmployeeDtoList(List<EmployeeEntity> employeeEntityList);
}
