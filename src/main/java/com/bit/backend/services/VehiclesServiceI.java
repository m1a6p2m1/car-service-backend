package com.bit.backend.services;

import com.bit.backend.dtos.VehiclesDto;

import java.util.List;

public interface VehiclesServiceI {
    VehiclesDto addVehiclesEntity(VehiclesDto vehiclesDto);
    List<VehiclesDto> getData();
    VehiclesDto updateForm(long id, VehiclesDto vehiclesDto);
    VehiclesDto deleteData(long id);
}
