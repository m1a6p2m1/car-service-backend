package com.bit.backend.dtos;

public class DefinedSubTaskDto {
    private Long id;
    private String subTaskName;
    private Long taskId;
    private Double subTaskPrice;

    public DefinedSubTaskDto() {
    }
    public DefinedSubTaskDto(Long id, String subTaskName, Long taskId, Double subTaskPrice) {
        this.id = id;
        this.subTaskName = subTaskName;
        this.taskId = taskId;
        this.subTaskPrice = subTaskPrice;
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
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Double getSubTaskPrice() {
        return subTaskPrice;
    }

    public void setSubTaskPrice(Double subTaskPrice) {
        this.subTaskPrice = subTaskPrice;
    }
}
