package com.bit.backend.dtos;

import java.time.LocalDate;

public class CustomerFeedbackDto {
    private long id;
    private String userName;
    private String licencePlate;

    private String uniqueTaskNo;
    private String serviceQuality;
    private String serviceType;
    private LocalDate serviceDate;
    private String recommendation;
    private String complaint;
    private String status;
    private Long userId;

    public CustomerFeedbackDto() {
    }

    public CustomerFeedbackDto(long id, String userName, String licencePlate, String uniqueTaskNo, String serviceQuality, String serviceType, LocalDate serviceDate, String recommendation, String complaint, String status, Long userId) {
        this.id = id;
        this.userName = userName;
        this.licencePlate = licencePlate;
        this.uniqueTaskNo = uniqueTaskNo;
        this.serviceQuality = serviceQuality;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.recommendation = recommendation;
        this.complaint = complaint;
        this.status = status;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getUniqueTaskNo() {
        return uniqueTaskNo;
    }

    public void setUniqueTaskNo(String uniqueTaskNo) {
        this.uniqueTaskNo = uniqueTaskNo;
    }

    public String getServiceQuality() {
        return serviceQuality;
    }

    public void setServiceQuality(String serviceQuality) {
        this.serviceQuality = serviceQuality;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
