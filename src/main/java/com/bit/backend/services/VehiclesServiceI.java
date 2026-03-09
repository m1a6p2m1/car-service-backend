package com.bit.backend.services;

import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;

import java.util.List;

public interface VehiclesServiceI {
    List<CustomerVehiclesDto> addVehiclesEntity(VehiclesDto VehiclesDto);
    List<CustomerVehiclesDto> getData();
    CustomerVehiclesDto updateForm(long id, CustomerVehiclesDto vehiclesDto);
    CustomerVehiclesDto deleteData(long id);
    List<CustomerVehiclesDto> getVehicleByCustomerId(Long customerId);
}
