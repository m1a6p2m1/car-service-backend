package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeLoginDto;
import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.EmployeeLoginEntity;
import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeLoginMapper;
import com.bit.backend.repositories.EmployeeLoginRepository;
import com.bit.backend.services.EmployeeLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeLoginService implements EmployeeLoginServiceI {
    private final EmployeeLoginRepository employeeLoginRepository;
    private final EmployeeLoginMapper employeeLoginMapper;

    public EmployeeLoginService(EmployeeLoginRepository employeeLoginRepository, EmployeeLoginMapper employeeLoginMapper) {
        this.employeeLoginRepository = employeeLoginRepository;
        this.employeeLoginMapper = employeeLoginMapper;
    }


    @Override
    public EmployeeLoginDto addEmployeeLoginEntity(EmployeeLoginDto employeeLoginDto) {
        //        System.out.println("-------------In BackEnd--------------");
        try {
            EmployeeLoginEntity employeeLoginEntity = employeeLoginMapper.toEmployeeLoginEntity(employeeLoginDto);
            EmployeeLoginEntity savedItem = employeeLoginRepository.save(employeeLoginEntity);
            EmployeeLoginDto savedDto = employeeLoginMapper.toEmployeeLoginDto(savedItem);
            return savedDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<EmployeeLoginDto> getData() {
        System.out.println("*******************In get Data**************");
        try {
            List<EmployeeLoginEntity> employeeLoginEntityList = employeeLoginRepository.findAll();
            List<EmployeeLoginDto> employeeLoginDtoList = employeeLoginMapper.toEmployeeLoginDtoList(employeeLoginEntityList);
            return employeeLoginDtoList;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeLoginDto updateForm(long id, EmployeeLoginDto employeeLoginDto) {
        System.out.println("*******************In Update Data**************");
        try {
            Optional<EmployeeLoginEntity> optionalEmployeeLoginEntity = employeeLoginRepository.findById(id);

            if(!optionalEmployeeLoginEntity.isPresent()){
                throw new AppException("Employee Login Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            EmployeeLoginEntity newoptionalEmployeeLoginEntity = employeeLoginMapper.toEmployeeLoginEntity(employeeLoginDto);

            newoptionalEmployeeLoginEntity.setId(id);

            EmployeeLoginEntity employeeLoginEntity = employeeLoginRepository.save(newoptionalEmployeeLoginEntity);
            EmployeeLoginDto responseEmployeeLoginDto = employeeLoginMapper.toEmployeeLoginDto(employeeLoginEntity);

            return responseEmployeeLoginDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
