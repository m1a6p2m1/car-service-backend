package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime timeSlot;
    private Integer bay;
    private Long bookedCount;
    private String taskName;
    private String vehicleType;
    private String serviceType;
    private String additionalServices;
    private String customerName;
    private String email;
    private String phoneNumber;
    private Double totalPrice;


    public AppointmentDto() {
    }

    public AppointmentDto(Long id, LocalDate appointmentDate, LocalTime timeSlot, Integer bay, Long bookedCount, String taskName, String vehicleType, String serviceType, String additionalServices, String customerName, String email, String phoneNumber, Double totalPrice) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.bay = bay;
        this.bookedCount = bookedCount;
        this.taskName = taskName;
        this.vehicleType = vehicleType;
        this.serviceType = serviceType;
        this.additionalServices = additionalServices;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(Long bookedCount) {
        this.bookedCount = bookedCount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //    public LocalDate appointmentDate() {
//        return appointmentDate;
//    }
//
//    public LocalTime timeSlot() {
//        return timeSlot;
//    }
}
