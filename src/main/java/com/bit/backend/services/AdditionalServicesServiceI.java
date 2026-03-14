package com.bit.backend.services;

import com.bit.backend.dtos.AdditionalServicesDto;

import java.util.List;

public interface AdditionalServicesServiceI {
    AdditionalServicesDto addData(AdditionalServicesDto additionalServicesDto);
    List<AdditionalServicesDto> getData();
    AdditionalServicesDto updateForm(long id, AdditionalServicesDto additionalServicesDto);
    AdditionalServicesDto deleteData(long id);
}
