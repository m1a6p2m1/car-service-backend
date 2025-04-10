package com.bit.backend.mappers;

import com.bit.backend.dtos.CustomerFeedbackDto;
import com.bit.backend.entities.CustomerFeedbackEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface CustomerFeedbackMapper {
    CustomerFeedbackDto toCustomerFeedbackDto(CustomerFeedbackEntity customerFeedbackEntity);
    CustomerFeedbackEntity toCustomerFeedbackEntity(CustomerFeedbackDto customerFeedbackDto);
    List<CustomerFeedbackDto> toCustomerFeedbackDtoList(List<CustomerFeedbackEntity> customerFeedbackEntityList);
}
