package com.bit.backend.controllers;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskAssignController {
    private  final TaskAssignServiceI taskAssignServiceI;

    public TaskAssignController(TaskAssignServiceI taskAssignServiceI) {
        this.taskAssignServiceI = taskAssignServiceI;
    }

    @GetMapping("/defined_tasks")
    public ResponseEntity<List<DefinedTasksDto>> getDefinedTasksData(){
        try {
            List<DefinedTasksDto> definedTasksDtos = taskAssignServiceI.getDefinedTasksData();
            return ResponseEntity.ok(definedTasksDtos);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/task-assign")
    public ResponseEntity<TaskAssignDto> addForm(@RequestBody TaskAssignDto taskAssignDto){
        try {
            TaskAssignDto taskAssignDtoResponse = taskAssignServiceI.addTaskAssignEntity(taskAssignDto);
            return ResponseEntity.created(URI.create("task-assign"+taskAssignDtoResponse.getTaskName().replace(" ","%20"))).body(taskAssignDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task-assign")
    public ResponseEntity<List<TaskAssignDto>> getData(){
        try {
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getData();
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/task-assign/{taskId}")
    public ResponseEntity<TaskAssignDto> updateData(@PathVariable long taskId, @RequestBody TaskAssignDto taskAssignDto){
        try {
            TaskAssignDto taskAssignDtoRsponse = taskAssignServiceI.updateData(taskId, taskAssignDto);
            return ResponseEntity.ok(taskAssignDtoRsponse);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/task-assign/{taskId}")
    public ResponseEntity<TaskAssignDto> deleteData(@PathVariable long taskId){
        try {
            TaskAssignDto taskAssignDto = taskAssignServiceI.deleteData(taskId);
            return ResponseEntity.ok(taskAssignDto);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
