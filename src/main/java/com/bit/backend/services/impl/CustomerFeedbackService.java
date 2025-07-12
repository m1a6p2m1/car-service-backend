package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.CustomerFeedbackDto;
import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.entities.CustomerFeedbackEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.CustomerFeedbackMapper;
import com.bit.backend.repositories.CustomerFeedbackRepository;
import com.bit.backend.services.CustomerFeedbackServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerFeedbackService implements CustomerFeedbackServiceI {
    private final CustomerFeedbackMapper customerFeedbackMapper;
    private final CustomerFeedbackRepository customerFeedbackRepository;

    public CustomerFeedbackService(CustomerFeedbackMapper customerFeedbackMapper, CustomerFeedbackRepository customerFeedbackRepository) {
        this.customerFeedbackMapper = customerFeedbackMapper;
        this.customerFeedbackRepository = customerFeedbackRepository;
    }

//    @Override
//    public CustomerFeedbackDto addCustomerFeedbackEntity(CustomerFeedbackDto customerFeedbackDto){
////        System.out.println("****************In Backend****************");
//        try {
//            CustomerFeedbackEntity customerFeedbackEntity = customerFeedbackMapper.toCustomerFeedbackEntity(customerFeedbackDto);
//            CustomerFeedbackEntity savedItem = customerFeedbackRepository.save(customerFeedbackEntity);
//            CustomerFeedbackDto savedDto = customerFeedbackMapper.toCustomerFeedbackDto(savedItem);
//
//            return savedDto;
//        }catch (Exception e){
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public List<CustomerFeedbackDto> getData(long id) {
//        System.out.println("****************In Backend****************");
        try {
            List<CustomerFeedbackEntity> customerFeedbackEntityList = customerFeedbackRepository.findAll();
            List<CustomerFeedbackDto> customerFeedbackDtoList = customerFeedbackMapper.toCustomerFeedbackDtoList(customerFeedbackEntityList);
            return customerFeedbackDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerFeedbackDto> getAllData() {
//        System.out.println("****************In Backend****************");
        try {
            List<CustomerFeedbackEntity> customerFeedbackEntityList = customerFeedbackRepository.findAll();
            List<CustomerFeedbackDto> customerFeedbackDtoList = customerFeedbackMapper.toCustomerFeedbackDtoList(customerFeedbackEntityList);
            return customerFeedbackDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public CustomerFeedbackDto getFeedbackById(long id) {
//        Optional<CustomerFeedbackEntity> optional = customerFeedbackRepository.findById(id);
//        if (!optional.isPresent()) {
//            throw new AppException("Customer Feedback not found with ID: " + id, HttpStatus.NOT_FOUND);
//        }
//
//        CustomerFeedbackEntity feedbackEntity = optional.get();
//        return customerFeedbackMapper.toCustomerFeedbackDto(feedbackEntity);
//    }

    @Override
    public CustomerFeedbackDto updateForm(long id, CustomerFeedbackDto customerFeedbackDto) {
//        System.out.println("******In DataBase**********");
        try {
            Optional<CustomerFeedbackEntity> optionalCustomerFeedbackEntity = customerFeedbackRepository.findById(id);
            if (!optionalCustomerFeedbackEntity.isPresent()){
                throw new AppException("Customer Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            CustomerFeedbackEntity newCustomerEntity = customerFeedbackMapper.toCustomerFeedbackEntity(customerFeedbackDto);
            newCustomerEntity.setId(id);
            CustomerFeedbackEntity customerFeedbackEntity = customerFeedbackRepository.save(newCustomerEntity);
            CustomerFeedbackDto customerFeedbackDtoResponse = customerFeedbackMapper.toCustomerFeedbackDto(customerFeedbackEntity);
            return customerFeedbackDtoResponse;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CustomerFeedbackDto deleteData(long id) {
        System.out.println("******In DataBase**********");
        try {
            Optional<CustomerFeedbackEntity> optionalCustomerFeedbackEntity = customerFeedbackRepository.findById(id);
            if (!optionalCustomerFeedbackEntity.isPresent()){
                throw new AppException("Customer Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            customerFeedbackRepository.deleteById(id);
            return customerFeedbackMapper.toCustomerFeedbackDto(optionalCustomerFeedbackEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
