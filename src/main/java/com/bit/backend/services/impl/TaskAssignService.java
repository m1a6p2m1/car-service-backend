package com.bit.backend.services.impl;

import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.entities.DefinedTasksEntity;
import com.bit.backend.entities.TaskAssignEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DefinedTasksMapper;
import com.bit.backend.mappers.TaskAssignMapper;
import com.bit.backend.repositories.DefinedTasksRepository;
import com.bit.backend.repositories.TaskAssignRepository;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskAssignService implements TaskAssignServiceI {
    private final TaskAssignRepository taskAssignRepository;
    private final TaskAssignMapper taskAssignMapper;
    private final DefinedTasksRepository definedTasksRepository;
    private final DefinedTasksMapper definedTasksMapper;

    public TaskAssignService(TaskAssignRepository taskAssignRepository, TaskAssignMapper taskAssignMapper,
                             DefinedTasksRepository definedTasksRepository, DefinedTasksMapper definedTasksMapper) {
        this.taskAssignRepository = taskAssignRepository;
        this.taskAssignMapper = taskAssignMapper;
        this.definedTasksRepository = definedTasksRepository;
        this.definedTasksMapper = definedTasksMapper;
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

    @Override
    public TaskAssignDto addTaskAssignEntity(TaskAssignDto taskAssignDto){
        try {
//            System.out.println("*******************In get Data**************");
            TaskAssignEntity taskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);
            TaskAssignEntity savedTask = taskAssignRepository.save(taskAssignEntity);
            TaskAssignDto savedDto = taskAssignMapper.toTaskAssignDto(savedTask);
            return savedDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
    public TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto) {
        try {
            Optional<TaskAssignEntity> optionalTaskAssignEntity = taskAssignRepository.findById(taskId);

            if(!optionalTaskAssignEntity.isPresent()){
                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            TaskAssignEntity newTaskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);

            newTaskAssignEntity.setTaskId(taskId);

            TaskAssignEntity taskAssignEntity = taskAssignRepository.save(newTaskAssignEntity);
            TaskAssignDto responseTaskAssignDto = taskAssignMapper.toTaskAssignDto(taskAssignEntity);

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
