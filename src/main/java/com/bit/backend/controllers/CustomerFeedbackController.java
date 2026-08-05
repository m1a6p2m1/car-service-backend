package com.bit.backend.controllers;

import com.bit.backend.dtos.CustomerFeedbackDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.CustomerFeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerFeedbackController {
    private final CustomerFeedbackServiceI customerFeedbackServiceI;


    public CustomerFeedbackController(CustomerFeedbackServiceI customerFeedbackServiceI) {
        this.customerFeedbackServiceI = customerFeedbackServiceI;
    }

    @PostMapping("/customer-feedback")
    public ResponseEntity<CustomerFeedbackDto> addForm(@RequestBody CustomerFeedbackDto customerFeedbackDto){
        try{
            System.out.println("****************In Backend Cus Feedback Controller****************");
            CustomerFeedbackDto customerFeedbackDtoResponse = customerFeedbackServiceI.addCustomerFeedbackEntity(customerFeedbackDto);
            return ResponseEntity.created(URI.create("/customer-feedback"+customerFeedbackDtoResponse.getId())).body(customerFeedbackDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/customer-feedback/{uniqueCusNo}")
    public ResponseEntity<List<CustomerFeedbackDto>> getCusFeedbackByCusNo(@PathVariable String uniqueCusNo){
        try {
            List<CustomerFeedbackDto> customerFeedbackDtoList = customerFeedbackServiceI.getCusFeedbackByCusNo(uniqueCusNo);
            return ResponseEntity.ok(customerFeedbackDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-feedback")
    public ResponseEntity<List<CustomerFeedbackDto>> getAllData(){
        try {
            List<CustomerFeedbackDto> customerFeedbackDtoList = customerFeedbackServiceI.getAllData();
            return ResponseEntity.ok(customerFeedbackDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/customer-feedback/{id}")
//    public ResponseEntity<CustomerFeedbackDto> getFeedbackById(@PathVariable long id) {
//        try {
//            CustomerFeedbackDto customerFeedbackDtoResponse = customerFeedbackServiceI.getFeedbackById(id);
//            return ResponseEntity.ok(customerFeedbackDtoResponse);
//        } catch (Exception e) {
//            throw new AppException("Failed to get feedback with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/customer-feedback/{id}")
    public ResponseEntity<CustomerFeedbackDto> updateForm(@PathVariable long id, @RequestBody CustomerFeedbackDto customerFeedbackDto){
        try {
            CustomerFeedbackDto customerFeedbackDtoResponse = customerFeedbackServiceI.updateForm(id, customerFeedbackDto);
            return ResponseEntity.ok(customerFeedbackDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer-feedback/{id}")
    public ResponseEntity<CustomerFeedbackDto> deleteData(@PathVariable long id){
        try {
            CustomerFeedbackDto customerFeedbackDto = customerFeedbackServiceI.deleteData(id);
            return ResponseEntity.ok(customerFeedbackDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update reviewed feedbacks
    @PutMapping("/customer-feedback/review/{id}")
    public ResponseEntity<CustomerFeedbackDto> updateReview(@PathVariable long id){
        try {
            CustomerFeedbackDto customerFeedbackDtoResponse = customerFeedbackServiceI.updateReview(id);
            return ResponseEntity.ok(customerFeedbackDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //feedback report
    @GetMapping("/customer-feedback/customer-feedbacks-rates")
    public ResponseEntity<List<Map<String, Object>>> getCustomerFeedbackRateServices() {
        try {
            List<Map<String, Object>> customerFeedbackRates = customerFeedbackServiceI.getCustomerFeedbackRateServices();
            return ResponseEntity.ok(customerFeedbackRates);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
