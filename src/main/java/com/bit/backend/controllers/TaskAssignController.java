package com.bit.backend.controllers;

import com.bit.backend.dtos.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.TaskAssignRepository;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
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
            TaskAssignDto taskAssignDtoResponse = taskAssignServiceI.   addTaskAssignEntity(taskAssignDto);
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

    //get tasks that assign to the supervisor into the task tracker
    @GetMapping("/task-assign/supervisor/tracker/{employeeId}/{taskNo}")
    public ResponseEntity<List<TaskAssignDto>> getSupervisorTasks(@PathVariable String employeeId, @PathVariable String taskNo){
        try {
            System.out.println("Task tracker supervisor tasks");
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getSupervisorTasks(employeeId, taskNo);
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e){
            throw new AppException(
                    "Request Failed with Error: " + e,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/all-assign-tasks/manager/tracker")
    public ResponseEntity<List<TaskAssignDto>> getAllTasks(){
        try {
            System.out.println("Task tracker supervisor tasks");
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getAllTasks();
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e){
            throw new AppException(
                    "Request Failed with Error: " + e,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/supervisor-task-by-employeeId")
    public ResponseEntity<List<TaskAssignDto>> getSupervisorTasksByEmployeeId(@RequestParam String employeeId) {
        // http://localhost:4200/task-by-uid
        try {
            System.out.println("Task tracker supervisor tasks by Supervisor");
            List<TaskAssignDto> taskAssignDtoList = taskAssignServiceI.getSupervisorTasksByEmployeeId(employeeId);
            return ResponseEntity.ok(taskAssignDtoList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task-assign/by-date-customerId")
    public ResponseEntity<List<TaskAssignDto>> getLicenseByDateAndCustomer(
            @RequestParam String date,
            @RequestParam Long userId) {

        LocalDate localDate = LocalDate.parse(date);

        return ResponseEntity.ok(
                taskAssignServiceI.getLicenseByDateAndCustomer(localDate, userId)
        );
    }
    //get Appointments details when select the license plate no for customer feedback
    @GetMapping("/task-assign/by-date-licensePlate")
    public ResponseEntity<TaskAssignDto> getDetailsByLicensePlate(
            @RequestParam String date,
            @RequestParam String licencePlate
    ){
        try {
            System.out.println("Controller reached: " + licencePlate);
            LocalDate localDate = LocalDate.parse(date);
            TaskAssignDto details = taskAssignServiceI.getDetailsByLicensePlate(localDate,licencePlate);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            throw new AppException("Failed to get appointment details " + licencePlate + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get done assigned tasks to the bill generate form table
    @GetMapping("/task-assign/done-status")
    public ResponseEntity<List<TaskAssignDto>> getAllDoneTasks(){
        return ResponseEntity.ok(taskAssignServiceI.getAllDoneTasks());
    }

    //update subtask prices when generate bill button click
    @PutMapping("/task-assign/{id}/sub-task-prices")
    public ResponseEntity<?> updateSubTasksPrices(@PathVariable Long id, @RequestBody TaskAssignDto dto) {
        try {

            taskAssignServiceI.updateSubTaskPrices(id, dto);

            return ResponseEntity.ok("Sub task prices updated successfully");


        }catch(Exception e){

            throw new AppException(
                    "Request Failed with Error: " + e,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }
    //Generate bill and save it in appointment table
    @PostMapping("/task-assign/{id}/generate-bill")
    public ResponseEntity<?> generateBill(@PathVariable Long id) throws Exception {
        taskAssignServiceI.generateBill(id);
        return ResponseEntity.ok("Bill Generated Successfully");
    }
}
