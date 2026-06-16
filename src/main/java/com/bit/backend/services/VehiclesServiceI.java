package com.bit.backend.services;

import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;

import java.util.List;
import java.util.Map;

public interface VehiclesServiceI {
    List<CustomerVehiclesDto> addVehiclesEntity(VehiclesDto VehiclesDto);
//    List<CustomerVehiclesDto> getData();
    List<Map<String, Object>> getData();
    CustomerVehiclesDto updateForm(long id, CustomerVehiclesDto vehiclesDto);
    CustomerVehiclesDto deleteData(long id);
    List<CustomerVehiclesDto> getVehicleByUserId(Long customerId);

    List<CustomerVehiclesDto> getVehiclesByCusId(String uniqueCusNo);

    boolean isLicencePlateIsExists(String licencePlate);
}
