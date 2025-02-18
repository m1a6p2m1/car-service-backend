package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.FormDemoDto;

import java.util.List;

public interface EmployeeServiceI {
    EmployeeDto addEmployeeEntity(EmployeeDto employeeDto);

    List<EmployeeDto> getData();

    EmployeeDto updateEmployeeData(long empNumber, EmployeeDto employeeDto);

    EmployeeDto deleteData(long empNumber);
}
