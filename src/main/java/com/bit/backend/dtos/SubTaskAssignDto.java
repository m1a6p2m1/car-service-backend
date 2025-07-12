package com.bit.backend.dtos;

public class SubTaskAssignDto {
    private Long id;
    private String description;
    private Long assignedUserId;
    private String uniqueSubTaskNo;
    private String status;

    public SubTaskAssignDto(Long id, String description, Long assignedUserId, String uniqueSubTaskNo, String status) {
        this.id = id;
        this.description = description;
        this.assignedUserId = assignedUserId;
        this.uniqueSubTaskNo = uniqueSubTaskNo;
        this.status = status;
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
}
