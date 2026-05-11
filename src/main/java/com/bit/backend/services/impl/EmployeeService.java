package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
import com.bit.backend.repositories.AttendanceMarkRepository;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;
    private final AttendanceMarkRepository attendanceMarkRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, UserRepository userRepository, AttendanceMarkRepository attendanceMarkRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userRepository = userRepository;
        this.attendanceMarkRepository = attendanceMarkRepository;
    }

    @Override
    public EmployeeDto addEmployeeEntity(EmployeeDto employeeDto) {
        try {
            System.out.println("****************In Backend****************");
            //convert DTO -> ENTITY
            EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            EmployeeEntity savedItem = employeeRepository.save(employeeEntity);
            //Generate Unique No
            String uniqueNo = generateEmpNumber(savedItem);
            savedItem.setUniqueEmpNo(uniqueNo);
            //Save again with unique No
            EmployeeEntity updateEntity = employeeRepository.save(savedItem);

            EmployeeDto savedDto = employeeMapper.toEmployeeDto(updateEntity);

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
    @Transactional
    public EmployeeDto deleteData(long empNumber) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(empNumber);

            if(!optionalEmployeeEntity.isPresent()){
                throw new AppException("Employee Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            attendanceMarkRepository.deleteByEmployee_EmpNumber(empNumber);
            userRepository.deleteByEmployee_EmpNumber(empNumber);
            employeeRepository.deleteById(empNumber);
            return employeeMapper.toEmployeeDto(optionalEmployeeEntity.get());
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get employee list to set supervisors list task_assign form supervisor field
    @Override
    public List<Map<String, Object>> getEmployees() {
        return employeeRepository.getEmployeeList();
    }

    @Override
    public List<Map<String, Object>> getEmployeeCountByJobRole() {
        return employeeRepository.getEmployeeCountByJobRole();
    }

    public String generateEmpNumber(EmployeeEntity employeeEntity) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String empNumberPart = String.valueOf(employeeEntity.getEmpNumber());
        String uniquePart = String.format("%03d", new Random().nextInt(1000)); // 000 - 999

        return "EMP" + datePart + empNumberPart + uniquePart;
    }
}
