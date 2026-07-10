package com.bit.backend.services;

import com.bit.backend.dtos.*;

import java.time.LocalDate;
import java.util.List;

public interface TaskAssignServiceI {
    TaskAssignDto addTaskAssignEntity(TaskAssignDto taskAssignDto);
    List<TaskAssignDto> getData();
    TaskAssignDto getTaskById(Long taskId);
    TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto);
    TaskAssignDto deleteData(long taskId);
    List<DefinedTasksDto> getDefinedTasksData();

    List<TaskAssignDto> getByCustomerId(Long customerId); // customer commonly used tasks loaded into the dashboard
    List<SubTaskAssignDto> getAssignedSubTasksData(Long userId);
    SubTaskStatusChangeDto subTaskStatusChange(SubTaskStatusChangeDto subTaskStatusChangeDto);
    List<TaskAssignDto> getMainTaskDetails(String customerId, String taskNo);
    List<TaskAssignDto> getMainTaskDetailsByUid(String uid);

    //get tasks that assign to the supervisor into the task tracker
    List<TaskAssignDto> getSupervisorTasks(String employeeId, String taskNo);
    List<TaskAssignDto> getSupervisorTasksByEmployeeId(String employeeId);

    //show all assign tasks for Manager
    List<TaskAssignDto> getAllTasks();

    List<TaskAssignDto> getLicenseByDateAndCustomer(LocalDate date, Long customerId);

    //get Appointments details when select the license plate no for customer feedback
    TaskAssignDto getDetailsByLicensePlate(LocalDate date, String licencePlate);

    List<TaskAssignDto> getAllDoneTasks();
}
