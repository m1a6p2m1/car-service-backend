package com.bit.backend.services.impl;

import com.bit.backend.dtos.DefinedSubTaskDto;
import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskIntroduceDto;
import com.bit.backend.entities.DefinedSubTaskEntity;
import com.bit.backend.entities.DefinedTasksEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DefinedTasksMapper;
import com.bit.backend.mappers.TaskMapper;
import com.bit.backend.repositories.DefinedSubTasksRepository;
import com.bit.backend.repositories.DefinedTasksRepository;
import com.bit.backend.services.TaskIntroduceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskIntroduceService implements TaskIntroduceServiceI {

    private final DefinedTasksRepository definedTasksRepository;
    private final DefinedSubTasksRepository definedSubTasksRepository;
    private final DefinedTasksMapper definedTasksMapper;

    public TaskIntroduceService(DefinedTasksRepository definedTasksRepository, DefinedSubTasksRepository definedSubTasksRepository, TaskMapper taskMapper, DefinedTasksMapper definedTasksMapper) {
        this.definedTasksRepository = definedTasksRepository;
        this.definedSubTasksRepository = definedSubTasksRepository;
        this.definedTasksMapper = definedTasksMapper;
    }

    @Override
    public TaskIntroduceDto saveTask(TaskIntroduceDto taskIntroduceDto) {
//        System.out.println("+++++++++++++++In The Backend+++++++++++++");
        // Create a new DefinedTasksEntity
        DefinedTasksEntity taskEntity = new DefinedTasksEntity();
        taskEntity.setTaskName(taskIntroduceDto.getTaskName());

        // Save task first to get generated ID
        DefinedTasksEntity savedTask = definedTasksRepository.save(taskEntity);

        // Save subtasks
        List<DefinedSubTaskEntity> subTaskEntities = definedTasksMapper.toDefinedSubTaskEntityList(taskIntroduceDto.getSubTasks());
        subTaskEntities.forEach(entity -> entity.setDefinedTasksEntity(savedTask));
        List<DefinedSubTaskEntity> savedSubTasks = definedSubTasksRepository.saveAll(subTaskEntities);
        List<DefinedSubTaskDto> savedSubTasksDto = definedTasksMapper.toDefinedSubTaskDto(savedSubTasks);


        TaskIntroduceDto response = new TaskIntroduceDto();
        response.setTaskName(savedTask.getTaskName());
        response.setSubTasks(savedSubTasksDto);
        return response;
    }

    @Override
    public List<TaskIntroduceDto> getData() {
//        System.out.println("+++++++++++++++In The Backend+++++++++++++");
        try {
            List<DefinedTasksEntity> tasks = definedTasksRepository.findAll();
            return definedTasksMapper.toTaskIntroduceDtoList(tasks);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public TaskIntroduceDto updateData(long id, TaskIntroduceDto taskIntroduceDto) {
//        System.out.println("+++++++++++++++In The Backend+++++++++++++");
//        return null;
//    }
}
