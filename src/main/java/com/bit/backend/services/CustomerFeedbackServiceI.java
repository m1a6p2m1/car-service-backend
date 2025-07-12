package com.bit.backend.services;

import com.bit.backend.dtos.CustomerFeedbackDto;

import java.util.List;

public interface CustomerFeedbackServiceI {
//    CustomerFeedbackDto addCustomerFeedbackEntity(CustomerFeedbackDto customerFeedbackDto);
    List<CustomerFeedbackDto> getData(long id);
    List<CustomerFeedbackDto> getAllData();
//    CustomerFeedbackDto getFeedbackById(long id);
    CustomerFeedbackDto updateForm(long id, CustomerFeedbackDto customerFeedbackDto);
    CustomerFeedbackDto deleteData(long id);
}
