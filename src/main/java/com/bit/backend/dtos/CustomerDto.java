package com.bit.backend.dtos;

public class CustomerDto {
    private long cusId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String gender;
    private String address;
    private String nic;
    private String licencePlate;
    private String vehicleType;
    private String vehicleModel;
    private boolean loginCreated;

    public CustomerDto() {
    }

    public CustomerDto(long cusId, String firstName, String lastName, String email, String contactNumber, String gender, String address, String nic, String licencePlate, String vehicleType, String vehicleModel, boolean loginCreated) {
        this.cusId = cusId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.address = address;
        this.nic = nic;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.loginCreated = loginCreated;
    }

    public long getCusId() {
        return cusId;
    }

    public void setCusId(long cusId) {
        this.cusId = cusId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public boolean isLoginCreated() {
        return loginCreated;
    }

    public void setLoginCreated(boolean loginCreated) {
        this.loginCreated = loginCreated;
    }
}
