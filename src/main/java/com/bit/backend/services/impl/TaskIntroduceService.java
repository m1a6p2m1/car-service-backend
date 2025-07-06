package com.bit.backend.services.impl;

import com.bit.backend.dtos.DefinedSubTaskDto;
import com.bit.backend.dtos.DefinedTasksDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.dtos.TaskIntroduceDto;
import com.bit.backend.entities.DefinedSubTaskEntity;
import com.bit.backend.entities.DefinedTasksEntity;
import com.bit.backend.entities.TaskAssignEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DefinedTasksMapper;
import com.bit.backend.mappers.TaskMapper;
import com.bit.backend.repositories.DefinedSubTasksRepository;
import com.bit.backend.repositories.DefinedTasksRepository;
import com.bit.backend.services.TaskIntroduceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//
//        List<DefinedTasksEntity> tasks = definedTasksRepository.findAllWithSubTasks();
//        return definedTasksMapper.toTaskIntroduceDtoList(tasks);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public TaskIntroduceDto getTaskById(Long id) {
        Optional<DefinedTasksEntity> optionalTask = definedTasksRepository.findById(id);

        if (optionalTask.isPresent()) {
            return definedTasksMapper.toTaskIntroduceDto(optionalTask.get());
        } else {
            throw new AppException("Task not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public TaskIntroduceDto updateData(long id, TaskIntroduceDto taskIntroduceDto) {
//        System.out.println("+++++++++++++++In The Backend+++++++++++++");
        try {
            Optional<DefinedTasksEntity> optionalTask = definedTasksRepository.findById(id);

            if (!optionalTask.isPresent()) {
                throw new AppException("Task Not Found", HttpStatus.NOT_FOUND);
            }

            DefinedTasksEntity existingTask = optionalTask.get();
            existingTask.setTaskName(taskIntroduceDto.getTaskName()); // Update main task fields here

            // Handle SubTasks
            List<DefinedSubTaskEntity> existingSubTasks = existingTask.getDefinedSubTaskEntities();
            List<DefinedSubTaskDto> incomingSubTaskDtos = taskIntroduceDto.getSubTasks();

            // Update or Add SubTasks
            List<DefinedSubTaskEntity> updatedSubTasks = new ArrayList<>();
            for (DefinedSubTaskDto dto : incomingSubTaskDtos) {
                if (dto.getId() != null) {
                    // Update existing subtask
                    DefinedSubTaskEntity match = existingSubTasks.stream()
                            .filter(st -> st.getId().equals(dto.getId()))
                            .findFirst()
                            .orElse(null);

                    if (match != null) {
                        match.setSubTaskName(dto.getSubTaskName());
                        updatedSubTasks.add(match);
                    }
                } else {
                    // New subtask
                    DefinedSubTaskEntity newSubTask = new DefinedSubTaskEntity();
                    newSubTask.setSubTaskName(dto.getSubTaskName());
                    newSubTask.setDefinedTasksEntity(existingTask);
                    updatedSubTasks.add(newSubTask);
                }
            }

            // Delete removed subtasks
            existingSubTasks.removeIf(existing ->
                    updatedSubTasks.stream().noneMatch(updated ->
                            updated.getId() != null && updated.getId().equals(existing.getId())
                    )
            );

            // Set the updated subtasks
            existingTask.setDefinedSubTaskEntities(updatedSubTasks);

            DefinedTasksEntity saved = definedTasksRepository.save(existingTask);
            return definedTasksMapper.toTaskIntroduceDto(saved);

        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TaskIntroduceDto deleteData(long id) {
        System.out.println("+++++++++++++++In The Backend+++++++++++++");
        try {
            Optional<DefinedTasksEntity> optionalDefinedTasksEntity = definedTasksRepository.findById(id);

            if(!optionalDefinedTasksEntity.isPresent()){
                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            definedTasksRepository.deleteById(id);

            // Return the deleted task as DTO
            return definedTasksMapper.toTaskIntroduceDto(optionalDefinedTasksEntity.get());

        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
