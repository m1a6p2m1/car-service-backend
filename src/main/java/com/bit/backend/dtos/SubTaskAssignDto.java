package com.bit.backend.dtos;

public class SubTaskAssignDto {
    private Long id;
    private String description;
    private Long assignedUserId;

    public SubTaskAssignDto(Long id, String description, Long assignedUserId) {
        this.id = id;
        this.description = description;
        this.assignedUserId = assignedUserId;
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
}
