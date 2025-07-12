package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DefinedTasksMapper;
import com.bit.backend.mappers.TaskAssignMapper;
import com.bit.backend.repositories.DefinedTasksRepository;
import com.bit.backend.repositories.SubTasksAssignRepository;
import com.bit.backend.repositories.TaskAssignRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.CustomerServiceI;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TaskAssignService implements TaskAssignServiceI {
    private final TaskAssignRepository taskAssignRepository;
    private final TaskAssignMapper taskAssignMapper;
    private final DefinedTasksRepository definedTasksRepository;
    private final DefinedTasksMapper definedTasksMapper;
    private final SubTasksAssignRepository subTasksAssignRepository;
    private final UserRepository userRepository;
    private final CustomerServiceI customerServiceI;

    public TaskAssignService(TaskAssignRepository taskAssignRepository, TaskAssignMapper taskAssignMapper,
                             DefinedTasksRepository definedTasksRepository, DefinedTasksMapper definedTasksMapper,
                             SubTasksAssignRepository subTasksAssignRepository, UserRepository userRepository,
                             CustomerServiceI customerServiceI) {
        this.taskAssignRepository = taskAssignRepository;
        this.taskAssignMapper = taskAssignMapper;
        this.definedTasksRepository = definedTasksRepository;
        this.definedTasksMapper = definedTasksMapper;
        this.subTasksAssignRepository = subTasksAssignRepository;
        this.userRepository = userRepository;
        this.customerServiceI = customerServiceI;
    }

    @Override
    public List<DefinedTasksDto> getDefinedTasksData() {
        try {
            List<DefinedTasksEntity> definedTasksEntities = definedTasksRepository.findAll();
            List<DefinedTasksDto> definedTasksDtos = definedTasksMapper.toDefinedTasksDtos(definedTasksEntities);
            return definedTasksDtos;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // customer commonly used tasks loaded into the dashboard
    @Override
    public List<TaskAssignDto> getByCustomerId(Long customerId) {
        try {
            List<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findByCustomerId(customerId);
            return taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SubTaskAssignDto> getAssignedSubTasksData(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new AppException("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR));
            if (user.getEmployee() == null) {
                throw new AppException("Invalid Employee. Please login with correct employee Id", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Long empId = user.getEmployee().getEmpNumber();

            List<SubTaskAssignedEntity> subTaskAssignedEntities = subTasksAssignRepository.findBySupervisor(empId);
            return taskAssignMapper.toSubTaskAssignDto(subTaskAssignedEntities);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SubTaskStatusChangeDto subTaskStatusChange(SubTaskStatusChangeDto subTaskStatusChangeDto) {
        try {
            SubTaskAssignDto savedSubTasks = null;
            Optional<SubTaskAssignedEntity> oSubTaskAssignedEntity = subTasksAssignRepository.findById(subTaskStatusChangeDto.getId());

            if (oSubTaskAssignedEntity.isPresent()) {
                SubTaskAssignedEntity subTaskAssignedEntity = oSubTaskAssignedEntity.get();
                subTaskAssignedEntity.setStatus(subTaskStatusChangeDto.getStatus());
                savedSubTasks = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.save(subTaskAssignedEntity));
            }

            return subTaskStatusChangeDto;
        } catch (Exception error) {
            throw new AppException("Request Failed with Error: " + error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TaskAssignDto> getMainTaskDetails(String customerId, String taskNo) {
        if (customerId.equals("-1") && taskNo.equals("-1")) {
            throw new AppException("Invalid Request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // get customerId from userId

        User user = userRepository.findById(Long.parseLong(customerId)).orElseThrow(() -> new AppException("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR));

        if (user.getId() != null) {
            Long cusId = user.getCustomer().getCusId();
            List<TaskAssignEntity> taskAssignEntityList = this.taskAssignRepository.findByCustomerId(cusId);
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        }

        if (taskNo != null || !taskNo.equals("") || !taskNo.equals(null)) {
            List<TaskAssignEntity> taskAssignEntityList = this.taskAssignRepository.findByUniqueTaskNo(taskNo);
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        }
        return null;
    }

    @Override
    public TaskAssignDto addTaskAssignEntity(TaskAssignDto taskAssignDto){
        try {
//            System.out.println("*******************In get Data**************");
            TaskAssignEntity taskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);

            if ((taskAssignDto.getEmail() == null || taskAssignDto.getEmail().equals("")) && taskAssignDto.getCustomerId() != null) {
                CustomerDto customerDto = customerServiceI.getCustomerById(taskAssignDto.getCustomerId());
                if (customerDto.getEmail() != null) {
                    taskAssignEntity.setEmail(customerDto.getEmail());
                }
            }

            TaskAssignEntity savedTask = taskAssignRepository.save(taskAssignEntity);
            TaskAssignDto savedDto = null;
            TaskAssignEntity updatedTask = null;
            int count = 1;

            String taskNo = generateTaskNumber(savedTask);
            Long superVisorId = taskAssignDto.getSupervisor();
            String customer = taskAssignDto.getCustomerName();

            if (taskNo != null) {
                taskAssignEntity.setUniqueTaskNo(taskNo);
                updatedTask = taskAssignRepository.save(taskAssignEntity);
                savedDto = taskAssignMapper.toTaskAssignDto(updatedTask);
            }

            if (savedDto != null) {
                List<SubTaskAssignedEntity> subTaskAssignedEntityList = updatedTask.getSubTasks();

                for (SubTaskAssignedEntity subTaskAssignedEntity: subTaskAssignedEntityList) {
                    String subTaskNo = generateSubTaskNumber(taskNo, subTaskAssignedEntity, count);
                    subTaskAssignedEntity.setUniqueSubTaskNo(subTaskNo);
                    subTaskAssignedEntity.setSupervisor(superVisorId);
                    subTaskAssignedEntity.setMainUniqueTaskNo(taskNo);
                    subTaskAssignedEntity.setCustomer(customer);
                    count = count + 1;
                }

                List<SubTaskAssignDto> subTaskAssignDtoList = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.saveAll(subTaskAssignedEntityList));
            }

            // send mail to customer [Todo]
            // send notification to employee [todo]

            return savedDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public String generateTaskNumber(TaskAssignEntity taskAssignEntity) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String taskIdPart = String.valueOf(taskAssignEntity.getId());
        String uniquePart = String.format("%03d", new Random().nextInt(1000)); // 000 - 999

        return datePart + taskIdPart + uniquePart;
    }

    public String generateSubTaskNumber(String mainTaskNo, SubTaskAssignedEntity subTaskAssignedEntity, int count) {
        return mainTaskNo + count;
    }

    @Override
    public List<TaskAssignDto> getData() {
        try {
            List<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findAll();
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public TaskAssignDto getTaskById(Long taskId) {
        Optional<TaskAssignEntity> optionalTask = taskAssignRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            return taskAssignMapper.toTaskAssignDto(optionalTask.get());
        } else {
            throw new AppException("Task not found with id: " + taskId, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto) {
        int count = 0;
        try {
            Optional<TaskAssignEntity> optionalTaskAssignEntity = taskAssignRepository.findById(taskId);

            if(!optionalTaskAssignEntity.isPresent()){
                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            count = optionalTaskAssignEntity.get().getSubTasks().size() + 1;
            taskAssignDto.setUniqueTaskNo(optionalTaskAssignEntity.get().getUniqueTaskNo());
            TaskAssignEntity newTaskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);

            newTaskAssignEntity.setId(taskId);
            String customer = newTaskAssignEntity.getCustomerName();
            newTaskAssignEntity.setUniqueTaskNo(taskAssignDto.getUniqueTaskNo());

            String uniqueTaskNo = optionalTaskAssignEntity.get().getUniqueTaskNo();
            if (uniqueTaskNo == null || uniqueTaskNo.equals("") || uniqueTaskNo.equals(null)) {
                uniqueTaskNo = generateTaskNumber(optionalTaskAssignEntity.get());
                newTaskAssignEntity.setUniqueTaskNo(uniqueTaskNo);
                count = 0;
            }

            TaskAssignEntity taskAssignEntity = taskAssignRepository.save(newTaskAssignEntity);
            TaskAssignDto responseTaskAssignDto = taskAssignMapper.toTaskAssignDto(taskAssignEntity);
            Long superVisorId = taskAssignEntity.getSupervisor();

            if (responseTaskAssignDto != null) {
                List<SubTaskAssignedEntity> subTaskAssignedEntityList = taskAssignEntity.getSubTasks();

                for (SubTaskAssignedEntity subTaskAssignedEntity: subTaskAssignedEntityList) {
                    String subTaskNo = "";
                    if (subTaskAssignedEntity.getUniqueSubTaskNo() == null || subTaskAssignedEntity.getUniqueSubTaskNo().equals("") || subTaskAssignedEntity.getUniqueSubTaskNo().equals(null)) {
                        subTaskNo  = generateSubTaskNumber(uniqueTaskNo, subTaskAssignedEntity, count);
                    }
                    subTaskAssignedEntity.setUniqueSubTaskNo(subTaskNo);
                    subTaskAssignedEntity.setSupervisor(superVisorId);
                    subTaskAssignedEntity.setMainUniqueTaskNo(uniqueTaskNo);
                    subTaskAssignedEntity.setCustomer(customer);
                    count = count + 1;
                }

                List<SubTaskAssignDto> subTaskAssignDtoList = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.saveAll(subTaskAssignedEntityList));
            }

            return responseTaskAssignDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TaskAssignDto deleteData(long taskId) {
        try {
            Optional<TaskAssignEntity> optionalTaskAssignEntity = taskAssignRepository.findById(taskId);

            if(!optionalTaskAssignEntity.isPresent()){
                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            taskAssignRepository.deleteById(taskId);
            return taskAssignMapper.toTaskAssignDto(optionalTaskAssignEntity.get());
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
