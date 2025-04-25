package com.bit.backend.dtos;

import java.math.BigDecimal;


public class DefinedSubTaskDto {
    private Long id;
    private String subTaskName;
    private Long taskId;
    private BigDecimal price;

    public DefinedSubTaskDto() {
    }

    public DefinedSubTaskDto(Long id, String subTaskName, Long taskId, BigDecimal price) {
        this.id = id;
        this.subTaskName = subTaskName;
        this.taskId = taskId;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
