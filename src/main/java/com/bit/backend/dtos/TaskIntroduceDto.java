package com.bit.backend.dtos;

import java.util.List;

public class TaskIntroduceDto {
    private long id;
    private String taskName;
    private List<DefinedSubTaskDto> subTasks;

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
}
