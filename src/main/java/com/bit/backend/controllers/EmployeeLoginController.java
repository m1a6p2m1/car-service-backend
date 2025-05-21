package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeLoginDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeLoginServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeLoginController {
    private final EmployeeLoginServiceI employeeLoginServiceI;

    public EmployeeLoginController(EmployeeLoginServiceI employeeLoginServiceI) {
        this.employeeLoginServiceI = employeeLoginServiceI;
    }

    @PostMapping("/employee-login")
    public ResponseEntity<EmployeeLoginDto> addForm(@RequestBody EmployeeLoginDto employeeLoginDto){
        try {
            EmployeeLoginDto employeeLoginDtoResponse = employeeLoginServiceI.addEmployeeLoginEntity(employeeLoginDto);
            return ResponseEntity.created(URI.create("/employee-login"+employeeLoginDtoResponse.getFirstName())).body(employeeLoginDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/employee-login")
    public ResponseEntity<List<EmployeeLoginDto>> getData(){
        try {
            List<EmployeeLoginDto> employeeLoginDtoList = employeeLoginServiceI.getData();
            return ResponseEntity.ok(employeeLoginDtoList);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee-login/{id}")
    public ResponseEntity<EmployeeLoginDto> updateForm(@PathVariable long id, @RequestBody EmployeeLoginDto employeeLoginDto){
        try {
            EmployeeLoginDto employeeLoginDtoResponse = employeeLoginServiceI.updateForm(id, employeeLoginDto);
            return ResponseEntity.ok(employeeLoginDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
