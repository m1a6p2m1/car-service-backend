package com.bit.backend.dtos;

public class DefinedSubTaskDto {
    private Long id;
    private String subTaskName;
    private String subTaskPrice;
    private Long taskId;

    public DefinedSubTaskDto() {
    }

    public DefinedSubTaskDto(Long id, String subTaskName, String subTaskPrice, Long taskId) {
        this.id = id;
        this.subTaskName = subTaskName;
        this.subTaskPrice = subTaskPrice;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getSubTaskPrice() {
        return subTaskPrice;
    }

    public void setSubTaskPrice(String subTaskPrice) {
        this.subTaskPrice = subTaskPrice;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
