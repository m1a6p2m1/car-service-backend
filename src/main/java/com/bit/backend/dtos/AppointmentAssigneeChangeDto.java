package com.bit.backend.dtos;

public class AppointmentAssigneeChangeDto {

    private Long id;
    private Long assignee;
    private String assigneeName;

    public AppointmentAssigneeChangeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
