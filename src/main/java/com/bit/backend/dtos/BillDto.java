package com.bit.backend.dtos;


import java.time.LocalDate;
import java.util.List;

public class BillDto {
    private String customerName;
    private String licencePlate;
    private LocalDate date;
    private String taskName;
    private String serviceType;
    private List<SubTaskAssignDto> subTasks;
    private Double totalCost;

    public BillDto() {
    }

    public BillDto(String customerName, String licencePlate, LocalDate date, String taskName, String serviceType, List<SubTaskAssignDto> subTasks, Double totalCost) {
        this.customerName = customerName;
        this.licencePlate = licencePlate;
        this.date = date;
        this.taskName = taskName;
        this.serviceType = serviceType;
        this.subTasks = subTasks;
        this.totalCost = totalCost;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<SubTaskAssignDto> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskAssignDto> subTasks) {
        this.subTasks = subTasks;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
