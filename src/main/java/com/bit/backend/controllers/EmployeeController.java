package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceI employeeServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI){
        this.employeeServiceI = employeeServiceI;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addForm(@RequestBody EmployeeDto employeeDto){
        try {
            EmployeeDto employeeDtoResponse = employeeServiceI.addEmployeeEntity(employeeDto);
            return ResponseEntity.created(URI.create("/employee"+employeeDtoResponse.getFullName())).body(employeeDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getData(){
        try {
            List<EmployeeDto> employeeDtoList = employeeServiceI.getData();
//            int i = 1/0;
            return ResponseEntity.ok(employeeDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee/{empNumber}")
    public ResponseEntity<EmployeeDto> updateEmployeeData(@PathVariable long empNumber, @RequestBody EmployeeDto employeeDto){
        try {
            EmployeeDto employeeDtoResponse = employeeServiceI.updateEmployeeData(empNumber, employeeDto);
            return ResponseEntity.ok(employeeDtoResponse);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employee/{empNumber}")
    public ResponseEntity<EmployeeDto> deleteData(@PathVariable long empNumber){
        try {
            EmployeeDto employeeDto = employeeServiceI.deleteData(empNumber);
            return ResponseEntity.ok(employeeDto);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
