package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto addEmployeeEntity(EmployeeDto employeeDto) {
        try {
            System.out.println("****************In Backend****************");
            EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            EmployeeEntity savedItem = employeeRepository.save(employeeEntity);
            EmployeeDto savedDto = employeeMapper.toEmployeeDto(savedItem);
            return savedDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<EmployeeDto> getData() {
//        System.out.println("employeeService");
        try {
            List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
            List<EmployeeDto> employeeDtoList = employeeMapper.toEmployeeDtoList(employeeEntityList);
            return employeeDtoList;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long empNumber) {
        Optional<EmployeeEntity> optional = employeeRepository.findById(empNumber);
        if (!optional.isPresent()) {
            throw new AppException("Employee not found with ID: " + empNumber, HttpStatus.NOT_FOUND);
        }

        EmployeeEntity employeeEntity = optional.get();
        return employeeMapper.toEmployeeDto(employeeEntity);
    }


    @Override
    public EmployeeDto updateEmployeeData(long empNumber, EmployeeDto employeeDto) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(empNumber);

            if (!optionalEmployeeEntity.isPresent()){
                throw new AppException("Employee Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            EmployeeEntity newEmployeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            newEmployeeEntity.setEmpNumber(empNumber);

            EmployeeEntity employeeEntity = employeeRepository.save(newEmployeeEntity);
            EmployeeDto responseEmployeeDto = employeeMapper.toEmployeeDto(employeeEntity);
            return responseEmployeeDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeDto deleteData(long empNumber) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(empNumber);

            if(!optionalEmployeeEntity.isPresent()){
                throw new AppException("Employee Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            employeeRepository.deleteById(empNumber);
            return employeeMapper.toEmployeeDto(optionalEmployeeEntity.get());
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Map<String, Object>> getEmployees() {
        return employeeRepository.getEmployeeList();
    }

    @Override
    public List<Map<String, Object>> getEmployeeCountByJobRole() {
        return employeeRepository.getEmployeeCountByJobRole();
    }
}
