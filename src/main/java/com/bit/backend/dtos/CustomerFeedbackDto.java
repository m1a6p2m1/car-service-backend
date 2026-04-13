package com.bit.backend.dtos;

public class CustomerFeedbackDto {
    private long id;
    private String userName;
    private String taskNumber;
    private String serviceQuality;
    private String serviceType;
    private String serviceDate;
    private String recommendation;
    private String complaint;
    private Long userId;

    public CustomerFeedbackDto() {
    }

    public CustomerFeedbackDto(long id, String userName, String taskNumber, String serviceQuality, String serviceType, String serviceDate, String recommendation, String complaint, Long userId) {
        this.id = id;
        this.userName = userName;
        this.taskNumber = taskNumber;
        this.serviceQuality = serviceQuality;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.recommendation = recommendation;
        this.complaint = complaint;
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

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
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

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
