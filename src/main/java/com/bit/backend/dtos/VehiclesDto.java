package com.bit.backend.dtos;

public class VehiclesDto {

    private long id;
    private String customerName;
    private long customerId;
    private String licencePlate;
    private String vehicleType;
    private String vehicleModel;

    public VehiclesDto() {
    }
    public VehiclesDto(long id, String customerName, long customerId, String licencePlate, String vehicleType, String vehicleModel) {
        this.id = id;
        this.customerName = customerName;
        this.customerId = customerId;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
