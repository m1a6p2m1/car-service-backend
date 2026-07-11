package com.bit.backend.services;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.dtos.TaskIntroduceDto;

import java.util.List;

public interface TaskIntroduceServiceI {
    TaskIntroduceDto saveTask(TaskIntroduceDto taskIntroduceDto);

    List<TaskIntroduceDto> getData();

    TaskIntroduceDto getTaskById(Long id);

    TaskIntroduceDto updateData(long id, TaskIntroduceDto taskIntroduceDto);

    TaskIntroduceDto deleteData(long id);

    TaskIntroduceDto getByTaskName(String taskName);
}
