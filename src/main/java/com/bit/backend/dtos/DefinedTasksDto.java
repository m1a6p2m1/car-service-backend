package com.bit.backend.dtos;

import java.util.List;

public class DefinedTasksDto {
    private Long id;
    private String taskName;
    public List<DefinedSubTaskDto> definedSubTaskDtos;
    public String totalTaskPrice;
    private String description;
    private String shortDescription;

    public DefinedTasksDto() {
    }

    public DefinedTasksDto(Long id, String taskName, List<DefinedSubTaskDto> definedSubTaskDtos, String totalTaskPrice, String description, String shortDescription) {
        this.id = id;
        this.taskName = taskName;
        this.definedSubTaskDtos = definedSubTaskDtos;
        this.totalTaskPrice = totalTaskPrice;
        this.description = description;
        this.shortDescription = shortDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<DefinedSubTaskDto> getDefinedSubTaskDtos() {
        return definedSubTaskDtos;
    }

    public void setDefinedSubTaskDtos(List<DefinedSubTaskDto> definedSubTaskDtos) {
        this.definedSubTaskDtos = definedSubTaskDtos;
    }

    public String getTotalTaskPrice() {
        return totalTaskPrice;
    }

    public void setTotalTaskPrice(String totalTaskPrice) {
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
