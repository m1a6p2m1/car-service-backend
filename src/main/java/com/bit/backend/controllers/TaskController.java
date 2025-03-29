package com.bit.backend.controllers;

import com.bit.backend.dtos.TaskDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.TaskServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TaskController {

    public final TaskServiceI taskServiceI;

    public TaskController(TaskServiceI taskServiceI) {
        this.taskServiceI = taskServiceI;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto) {
        try {
            TaskDto savedTaskDto = taskServiceI.addTask(taskDto);
            return ResponseEntity.created(URI.create("task"+savedTaskDto.getTaskName())).body(savedTaskDto);
        } catch (Exception e) {
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
