package com.bit.backend.dtos;

public class CustomerVehiclesDto {

    private Long id;
    private Long customerId;
    private String customerName;
    private String licencePlate;
    private String vehicleType;
    private String vehicleModel;

    public CustomerVehiclesDto() {
    }

    public CustomerVehiclesDto(Long id, Long customerId, String customerName, String licencePlate, String vehicleType, String vehicleModel) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
