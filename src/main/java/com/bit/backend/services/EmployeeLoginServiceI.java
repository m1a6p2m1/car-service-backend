package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeLoginDto;

import java.util.List;

public interface EmployeeLoginServiceI {
    EmployeeLoginDto addEmployeeLoginEntity(EmployeeLoginDto employeeLoginDto);
    List<EmployeeLoginDto> getData();
    EmployeeLoginDto updateForm(long id,EmployeeLoginDto employeeLoginDto);
}
