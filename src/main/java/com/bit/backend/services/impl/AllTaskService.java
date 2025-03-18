package com.bit.backend.services.impl;

import com.bit.backend.dtos.AllTaskDto;
import com.bit.backend.entities.AllTaskEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AllTaskMapper;
import com.bit.backend.repositories.AllTaskRepository;
import com.bit.backend.services.AllTaskServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllTaskService implements AllTaskServiceI {
    private final AllTaskRepository allTaskRepository;
    private final AllTaskMapper allTaskMapper;

    public AllTaskService(AllTaskRepository allTaskRepository, AllTaskMapper allTaskMapper) {
        this.allTaskRepository = allTaskRepository;
        this.allTaskMapper = allTaskMapper;
    }

    @Override
    public AllTaskDto addAllTask(AllTaskDto allTaskDto){
//        System.out.println("*******************In add Data**************");
        AllTaskEntity allTaskEntity = allTaskMapper.toAllTAskEntity(allTaskDto);
        AllTaskEntity saveItem = allTaskRepository.save(allTaskEntity);
        AllTaskDto saveDto = allTaskMapper.toAllTaskDto(saveItem);
        return saveDto;
    }

    @Override
    public List<AllTaskDto> getData() {
//        System.out.println("*******************In add Data**************");
        List<AllTaskEntity> allTaskEntityList = allTaskRepository.findAll();
        List<AllTaskDto> allTaskDtoList = allTaskMapper.toAllTaskDtoList(allTaskEntityList);
        return allTaskDtoList;
    }

    @Override
    public AllTaskDto updateForm(long allTaskId, AllTaskDto allTaskDto) {
//        System.out.println("*******************In update Data**************");
        Optional<AllTaskEntity> optionalAllTaskEntity = allTaskRepository.findById(allTaskId);

        if (!optionalAllTaskEntity.isPresent()){
            throw new AppException("All Tasks Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        AllTaskEntity newAllTaskEntity = allTaskMapper.toAllTAskEntity(allTaskDto);
        newAllTaskEntity.setAllTaskId(allTaskId);

        AllTaskEntity allTaskEntity = allTaskRepository.save(newAllTaskEntity);
        AllTaskDto responseAllTaskDto = allTaskMapper.toAllTaskDto(allTaskEntity);
        return responseAllTaskDto;
    }
}
