package com.bit.backend.dtos;

public class SubTaskAssignDto {
    private Long id;
    private String description;
    private Long assignedUserId;
    private String uniqueSubTaskNo;
    private String status;
    private Long supervisor;
    private String assigneUserName;
    private String mainUniqueTaskNo;
    private String customer;

    public SubTaskAssignDto(Long id, String description, Long assignedUserId, String uniqueSubTaskNo, String status,
                            Long supervisor, String assigneUserName, String mainUniqueTaskNo, String customer) {
        this.id = id;
        this.description = description;
        this.assignedUserId = assignedUserId;
        this.uniqueSubTaskNo = uniqueSubTaskNo;
        this.status = status;
        this.supervisor = supervisor;
        this.assigneUserName = assigneUserName;
        this.mainUniqueTaskNo = mainUniqueTaskNo;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getUniqueSubTaskNo() {
        return uniqueSubTaskNo;
    }

    public void setUniqueSubTaskNo(String uniqueSubTaskNo) {
        this.uniqueSubTaskNo = uniqueSubTaskNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    public String getAssigneUserName() {
        return assigneUserName;
    }

    public void setAssigneUserName(String assigneUserName) {
        this.assigneUserName = assigneUserName;
    }

    public String getMainUniqueTaskNo() {
        return mainUniqueTaskNo;
    }

    public void setMainUniqueTaskNo(String mainUniqueTaskNo) {
        this.mainUniqueTaskNo = mainUniqueTaskNo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
