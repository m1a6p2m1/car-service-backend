package com.bit.backend.controllers;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.SubTaskAssignDto;
import com.bit.backend.dtos.SubTaskStatusChangeDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.entities.TaskAssignEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.TaskAssignRepository;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskAssignController {
    private  final TaskAssignServiceI taskAssignServiceI;
    private final TaskAssignRepository taskAssignRepository;
    public TaskAssignController(TaskAssignServiceI taskAssignServiceI, TaskAssignRepository taskAssignRepository) {
        this.taskAssignServiceI = taskAssignServiceI;
        this.taskAssignRepository = taskAssignRepository;
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

    @GetMapping("/task-assign/{taskId}")
    public ResponseEntity<TaskAssignDto> getTaskById(@PathVariable Long taskId) {
        try {
            TaskAssignDto taskAssignDto = taskAssignServiceI.getTaskById(taskId);
            return ResponseEntity.ok(taskAssignDto);
        } catch (Exception e) {
            throw new AppException("Failed to get task with id " + taskId + ". Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

    // customer commonly used tasks loaded into the dashboard
    @GetMapping("/task-assign/{customerId}")
    public ResponseEntity<List<TaskAssignDto>> getByCustomerId(@PathVariable Long customerId) {
        try {
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getByCustomerId(customerId);
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sub-task-assign/{userId}")
    public ResponseEntity<List<SubTaskAssignDto>> getData(@PathVariable Long userId){
        try {
            List<SubTaskAssignDto> subTaskAssignDtoList = taskAssignServiceI.getAssignedSubTasksData(userId);
            return ResponseEntity.ok(subTaskAssignDtoList);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/change-sub-task-status")
    public ResponseEntity<SubTaskStatusChangeDto> subTaskStatusChange(@RequestBody SubTaskStatusChangeDto subTaskStatusChangeDto){
        try {
            SubTaskStatusChangeDto subTaskStatusChange = taskAssignServiceI.subTaskStatusChange(subTaskStatusChangeDto);
            return ResponseEntity.ok(subTaskStatusChange);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/task-assign/tracker/{customerId}/{taskNo}")
    public ResponseEntity<List<TaskAssignDto>> getMainTaskDetails(@PathVariable String customerId, @PathVariable String taskNo) {
        try {
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getMainTaskDetails(customerId, taskNo);
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-task-by-uid")
    public ResponseEntity<List<TaskAssignDto>> getMainTaskDetailsByUid(@RequestParam String uid) {
        // http://localhost:4200/task-by-uid
        try {
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getMainTaskDetailsByUid(uid);
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
