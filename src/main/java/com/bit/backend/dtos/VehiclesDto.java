package com.bit.backend.dtos;

import java.util.List;

public class VehiclesDto {

    private long id;
    private long customerId;
    private List<CustomerVehiclesDto> vehicles;

    public VehiclesDto() {
    }

    public VehiclesDto(long id, long customerId, List<CustomerVehiclesDto> vehicles) {
        this.id = id;
        this.customerId = customerId;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<CustomerVehiclesDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<CustomerVehiclesDto> vehicles) {
        this.vehicles = vehicles;
    }
}
