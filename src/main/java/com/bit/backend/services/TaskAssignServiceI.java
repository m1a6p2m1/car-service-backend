package com.bit.backend.services;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;

import java.util.List;

public interface TaskAssignServiceI {
    TaskAssignDto addTaskAssignEntity(TaskAssignDto taskAssignDto);
    List<TaskAssignDto> getData();
    TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto);
    TaskAssignDto deleteData(long taskId);
    List<DefinedTasksDto> getDefinedTasksData();
}
