package com.bit.backend.services;

import com.bit.backend.dtos.CustomerDto;

import java.util.List;
import java.util.Map;

public interface CustomerServiceI {
    CustomerDto addCustomerEntity(CustomerDto customerDto);
    List<CustomerDto> getData();
    CustomerDto updateForm(long cusId, CustomerDto customerDto);
    CustomerDto deleteData(long cusId);
    List<Map<String, Object>> getTaskListCustomers();
}
