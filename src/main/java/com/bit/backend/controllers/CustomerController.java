package com.bit.backend.controllers;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.CustomerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    private final CustomerServiceI customerServiceI;

    public CustomerController(CustomerServiceI customerServiceI) {
        this.customerServiceI = customerServiceI;
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> addForm(@RequestBody CustomerDto customerDto){
        try{
            CustomerDto customerDtoResponse = customerServiceI.addCustomerEntity(customerDto);
            return ResponseEntity.created(URI.create("/customer"+customerDtoResponse.getFirstName())).body(customerDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getData(){
        try {
            List<CustomerDto> customerDtoList = customerServiceI.getData();
            return ResponseEntity.ok(customerDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customer/{cusId}")
    public ResponseEntity<CustomerDto> updateForm(@PathVariable long cusId, @RequestBody CustomerDto customerDto){
        try {
            CustomerDto customerDtoResponse = customerServiceI.updateForm(cusId, customerDto);
            return ResponseEntity.ok(customerDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer/{cusId}")
    public ResponseEntity<CustomerDto> deleteData(@PathVariable long cusId){
        try {
            CustomerDto customerDto = customerServiceI.deleteData(cusId);
            return ResponseEntity.ok(customerDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/task-list-customers")
    public ResponseEntity<List<Map<String, Object>>> getTaskListCustomers(){
        try {
            List<Map<String, Object>> taskListCustomerLIst = customerServiceI.getTaskListCustomers();
            return ResponseEntity.ok(taskListCustomerLIst);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
