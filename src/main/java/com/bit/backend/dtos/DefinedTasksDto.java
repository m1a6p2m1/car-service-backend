package com.bit.backend.dtos;

import java.util.List;

public class DefinedTasksDto {
    private Long id;
    private String taskName;
    public List<DefinedSubTaskDto> definedSubTaskDtos;

    public DefinedTasksDto() {
    }

    public DefinedTasksDto(Long id, String taskName, List<DefinedSubTaskDto> definedSubTaskDtos) {
        this.id = id;
        this.taskName = taskName;
        this.definedSubTaskDtos = definedSubTaskDtos;
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
}
