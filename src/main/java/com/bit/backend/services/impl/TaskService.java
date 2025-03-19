package com.bit.backend.services.impl;

import com.bit.backend.dtos.TaskDto;
import com.bit.backend.entities.Task;
import com.bit.backend.mappers.TaskMapper;
import com.bit.backend.repositories.TaskRepository;
import com.bit.backend.services.TaskServiceI;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements TaskServiceI {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto addTask(TaskDto taskDto) {
        Task taskEntity = taskMapper.toTaskEntity(taskDto);
        Task savedTaskEntity = taskRepository.save(taskEntity);
        return taskMapper.toTaskDto(savedTaskEntity);
    }
}
