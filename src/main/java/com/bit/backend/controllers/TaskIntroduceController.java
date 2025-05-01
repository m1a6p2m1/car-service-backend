package com.bit.backend.controllers;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.dtos.TaskIntroduceDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.TaskIntroduceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskIntroduceController {
    private final TaskIntroduceServiceI taskIntroduceServiceI;

    public TaskIntroduceController(TaskIntroduceServiceI taskIntroduceServiceI) {
        this.taskIntroduceServiceI = taskIntroduceServiceI;
    }
    @PostMapping("/task-introduce")
    public ResponseEntity<TaskIntroduceDto> addTask(@RequestBody TaskIntroduceDto taskIntroduceDto){
        TaskIntroduceDto taskIntroduceDtoResponse = taskIntroduceServiceI.saveTask(taskIntroduceDto);
//        return ResponseEntity.created(URI.create("/task-introduce"+ taskIntroduceDtoResponse.getTaskName())).body(taskIntroduceDtoResponse);
        return ResponseEntity.created(URI.create("/task-introduce"+taskIntroduceDtoResponse.getTaskName().replace(" ","%20"))).body(taskIntroduceDtoResponse);
    }
    @GetMapping("/task-introduce")
    public ResponseEntity<List<TaskIntroduceDto>> getData(){
        try {
            List<TaskIntroduceDto> taskIntroduceDtoList = taskIntroduceServiceI.getData();
            return ResponseEntity.ok(taskIntroduceDtoList);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/task-introduce/{id}")
    public ResponseEntity<TaskIntroduceDto> updateData(@PathVariable long id, @RequestBody TaskIntroduceDto taskIntroduceDto){
        try {
            TaskIntroduceDto taskIntroduceDtoRsponse = taskIntroduceServiceI.updateData(id, taskIntroduceDto);
            return ResponseEntity.ok(taskIntroduceDtoRsponse);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/task-introduce/{id}")
    public ResponseEntity<TaskIntroduceDto> deleteData(@PathVariable long id){
        try {
            TaskIntroduceDto taskIntroduceDto = taskIntroduceServiceI.deleteData(id);
            return ResponseEntity.ok(taskIntroduceDto);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
