package com.bit.backend.services;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.FormDemoDto;

import java.util.List;
import java.util.Map;
public interface EmployeeServiceI {
    EmployeeDto addEmployeeEntity(EmployeeDto employeeDto);

    List<EmployeeDto> getData();

    EmployeeDto getEmployeeById(Long empNumber);

    EmployeeDto updateEmployeeData(long empNumber, EmployeeDto employeeDto);

    EmployeeDto deleteData(long empNumber);

    //get employee list to set supervisors list task_assign form supervisor field
    List<Map<String, Object>> getEmployees();
    List<Map<String, Object>>getEmployeeCountByJobRole();

    //update Employee status "Inactive"
    EmployeeDto updateEmpStatus(long empNumber);

    //check phoneNumber is already exist
    boolean isPhoneNumberIsExists(String phoneNumber);
    boolean isNicIsExists(String nic);
}
