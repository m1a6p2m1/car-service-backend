package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.entities.CustomerEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.CustomerMapper;
import com.bit.backend.repositories.CustomerRepository;
import com.bit.backend.services.CustomerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceI {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto addCustomerEntity(CustomerDto customerDto) {
//        System.out.println("----------In Backend-----------");
        try {
            CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerDto);
            CustomerEntity savedItem = customerRepository.save(customerEntity);
            CustomerDto savedDto = customerMapper.toCustomerDto(savedItem);

            return savedDto;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerDto> getData() {
//        System.out.println("******In DataBase**********");
        try {
            List<CustomerEntity> customerEntityList = customerRepository.findAll();
            List<CustomerDto> customerDtoList = customerMapper.toCustomerDtoList(customerEntityList);
            return customerDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CustomerDto updateForm(long cusId, CustomerDto customerDto) {
//        System.out.println("******In DataBase**********");
        try {
            Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(cusId);
            if (!optionalCustomerEntity.isPresent()){
                throw new AppException("Customer Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            CustomerEntity newCustomerEntity = customerMapper.toCustomerEntity(customerDto);
            newCustomerEntity.setCusId(cusId);
            CustomerEntity customerEntity = customerRepository.save(newCustomerEntity);
            CustomerDto customerDtoResponse = customerMapper.toCustomerDto(customerEntity);
            return customerDtoResponse;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CustomerDto deleteData(long cusId) {
//        System.out.println("******In DataBase**********");
        try {
            Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(cusId);
            if (!optionalCustomerEntity.isPresent()){
                throw new AppException("Customer Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            customerRepository.deleteById(cusId);
            return customerMapper.toCustomerDto(optionalCustomerEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Map<String, Object>> getTaskListCustomers() {
        return customerRepository.getTaskCustomerList();
    }
}
