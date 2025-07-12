package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Integer bay;
    private Long bookedCount;
    private String taskName;
    private String vehicleType;
    private String serviceType;
    private String additionalServices;
    private String customerName;
    private String email;
    private String phoneNumber;
    private Double totalServicePrice;
    private Long assignee;
    private String assigneeName;


    public AppointmentDto() {
    }

    public AppointmentDto(Long id, LocalDate date, LocalTime time, Integer bay, Long bookedCount, String taskName, String vehicleType, String serviceType, String additionalServices, String customerName, String email, String phoneNumber, Double totalServicePrice, Long assignee, String assigneeName) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.bay = bay;
        this.bookedCount = bookedCount;
        this.taskName = taskName;
        this.vehicleType = vehicleType;
        this.serviceType = serviceType;
        this.additionalServices = additionalServices;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.totalServicePrice = totalServicePrice;
        this.assignee = assignee;
        this.assigneeName = assigneeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }

    public Long getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(Long bookedCount) {
        this.bookedCount = bookedCount;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Double getTotalServicePrice() {
        return totalServicePrice;
    }

    public void setTotalServicePrice(Double totalServicePrice) {
        this.totalServicePrice = totalServicePrice;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }
//    public LocalDate appointmentDate() {
//        return appointmentDate;
//    }
//
//    public LocalTime timeSlot() {
//        return timeSlot;
//    }
}
