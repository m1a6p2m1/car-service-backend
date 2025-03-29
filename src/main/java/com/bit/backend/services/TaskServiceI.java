package com.bit.backend.services;

import com.bit.backend.dtos.TaskDto;

public interface TaskServiceI {
    TaskDto addTask(TaskDto taskDto);
}
