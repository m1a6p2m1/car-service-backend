package com.bit.backend.services;

import com.bit.backend.dtos.CustomerDto;
import com.bit.backend.dtos.CustomerFeedbackDto;

import java.util.List;

public interface CustomerFeedbackServiceI {
    CustomerFeedbackDto addCustomerFeedbackEntity(CustomerFeedbackDto customerFeedbackDto);
    List<CustomerFeedbackDto> getData(String userName);
    CustomerFeedbackDto updateForm(long id, CustomerFeedbackDto customerFeedbackDto);
    CustomerFeedbackDto deleteData(long id);
}
