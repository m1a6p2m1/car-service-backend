package com.bit.backend.controllers;
import com.bit.backend.config.UserAuthProvider;
import com.bit.backend.dtos.CustomerFeedbackDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.SignUpDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeServiceI;
import com.bit.backend.services.UserServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Map;
@RestController
public class EmployeeController {
    private final EmployeeServiceI employeeServiceI;
    private final UserServiceI userServiceI;
    private final UserAuthProvider userAuthProvider;

    public EmployeeController(EmployeeServiceI employeeServiceI, UserServiceI userServiceI, UserAuthProvider userAuthProvider){
        this.employeeServiceI = employeeServiceI;
        this.userServiceI = userServiceI;
        this.userAuthProvider = userAuthProvider;
    }
    @PostMapping(value = {"/employee"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDto> addForm(@RequestPart("employeeForm") EmployeeDto employeeDto, @RequestPart("image") MultipartFile file
    ){
        try {
            employeeDto.setImage(file.getBytes());
            employeeDto.setImageName(file.getOriginalFilename());
            employeeDto.setImageType(file.getContentType());

            EmployeeDto employeeDtoResponse = employeeServiceI.addEmployeeEntity(employeeDto);
            return ResponseEntity.created(URI.create("/employee"+ employeeDtoResponse.getCallingName())).body(employeeDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getData(){
        try {
            List<EmployeeDto> employeeDtoList = employeeServiceI.getData();
            return ResponseEntity.ok(employeeDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/employee/{empNumber}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long empNumber) {
        try {
            EmployeeDto employeeDto = employeeServiceI.getEmployeeById(empNumber);
            return ResponseEntity.ok(employeeDto);
        } catch (Exception e) {
            throw new AppException("Failed to get employee with ID " + empNumber + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/employee/{empNumber}")
    public ResponseEntity<EmployeeDto> updateEmployeeData(
            @PathVariable long empNumber, @RequestPart("employeeForm") EmployeeDto employeeDto, @RequestPart("image") MultipartFile file
    ){
        try {
            employeeDto.setImage(file.getBytes());
            employeeDto.setImageName(file.getOriginalFilename());
            employeeDto.setImageType(file.getContentType());

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
    // employee logins
    @PostMapping("/employee/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userServiceI.register(signUpDto);
        return ResponseEntity.created(URI.create("/employee/register/" + user.getId())).body(user);
    }
    @PutMapping ("/employee/register/{empNumber}")
    public ResponseEntity<UserDto> editRegistrationDetails(@PathVariable long empNumber, @RequestBody SignUpDto signUpDto) {
        UserDto user = userServiceI.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
    //get employee list to set supervisors list task_assign form supervisor field
    @GetMapping("/employee/get-employee-list")
    public ResponseEntity<List<Map<String, Object>>> getEmployees(){
        try {
            List<Map<String, Object>> employeeList = employeeServiceI.getEmployees();
            return ResponseEntity.ok(employeeList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Report employee count by job role
    @GetMapping("/employee/get-employee-count-by-role")
    public ResponseEntity<List<Map<String, Object>>> getEmployeeCountByJobRole(){
        try {
            List<Map<String, Object>> employeeList = employeeServiceI.getEmployeeCountByJobRole();
            return ResponseEntity.ok(employeeList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Enable and Disable the Employee Login (EmployeeStatus update to the Active and Inactive)
    @PutMapping("/employee/update-status/{empNumber}")
    public ResponseEntity<EmployeeDto> updateEmpStatus(@PathVariable long empNumber){
        try {
            EmployeeDto employeeDtoResponse = employeeServiceI.updateEmpStatus(empNumber);
            return ResponseEntity.ok(employeeDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PutMapping("/employee/update-login-status/{empNumber}")
//    public ResponseEntity<EmployeeDto> updateEmpLoginStatus(@PathVariable long empNumber){
//        try {
//            EmployeeDto employeeDtoResponse = employeeServiceI.updateEmpStatus(empNumber);
//            return ResponseEntity.ok(employeeDtoResponse);
//        }catch (Exception e){
//            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    //check phoneNumber is already exist
    @GetMapping("/check-phone")
    public ResponseEntity<Boolean> checkPhoneNumber(@RequestParam String phoneNumber){
        boolean exists = employeeServiceI.isPhoneNumberIsExists(phoneNumber);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/check-nic")
    public ResponseEntity<Boolean> checkNicNumber(@RequestParam String nic){
        boolean exists = employeeServiceI.isNicIsExists(nic);
        return ResponseEntity.ok(exists);
    }
}
