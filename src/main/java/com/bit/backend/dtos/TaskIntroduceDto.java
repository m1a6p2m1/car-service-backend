package com.bit.backend.dtos;

import java.util.List;

public class TaskIntroduceDto {
    private long id;
    private String taskName;
    private List<DefinedSubTaskDto> subTasks;
    private Double totalTaskPrice;
    private String description;
    private String shortDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<DefinedSubTaskDto> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<DefinedSubTaskDto> subTasks) {
        this.subTasks = subTasks;
    }

    public Double getTotalTaskPrice() {
        return totalTaskPrice;
    }

    public void setTotalTaskPrice(Double totalTaskPrice) {
        this.totalTaskPrice = totalTaskPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
