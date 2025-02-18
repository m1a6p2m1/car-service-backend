package com.bit.backend.services.impl;

import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FormDemoMapper;
import com.bit.backend.repositories.FormDemoRepository;
import com.bit.backend.services.FormDemoServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormDemoService implements FormDemoServiceI {

    private final FormDemoRepository formDemoRepository;
    private final FormDemoMapper formDemoMapper;

    public FormDemoService(FormDemoRepository formDemoRepository, FormDemoMapper formDemoMapper) {
        this.formDemoRepository = formDemoRepository;
        this.formDemoMapper = formDemoMapper;
    }
    @Override
    public FormDemoDto addFormDemoEntity(FormDemoDto formDemoDto) {
//        System.out.println("-------------In BackEnd--------------");
        try {
            FormDemoEntity formDemoEntity = formDemoMapper.toFormDemoEntity(formDemoDto);
            FormDemoEntity savedItem = formDemoRepository.save(formDemoEntity);
            FormDemoDto savedDto = formDemoMapper.toFormDemoDto(savedItem);
            return savedDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FormDemoDto> getData() {
//        System.out.println("*******************In get Data**************");
        try {
            List<FormDemoEntity> formDemoEntityList = formDemoRepository.findAll();
            List<FormDemoDto> formDemoDtoList = formDemoMapper.toFormDemoDtoList(formDemoEntityList);
            return formDemoDtoList;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FormDemoDto updateForm(long id, FormDemoDto formDemoDto) {
        try {
            Optional<FormDemoEntity> optionalFormDemoEntity = formDemoRepository.findById(id);

            if(!optionalFormDemoEntity.isPresent()){
                throw new AppException("Form Demo Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            FormDemoEntity newFormDemoEntity = formDemoMapper.toFormDemoEntity(formDemoDto);

            newFormDemoEntity.setId(id);

            FormDemoEntity formDemoEntity = formDemoRepository.save(newFormDemoEntity);
            FormDemoDto responseFormDemoDto = formDemoMapper.toFormDemoDto(formDemoEntity);

            return responseFormDemoDto;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FormDemoDto deleteData(long id) {
        try {
            Optional<FormDemoEntity> optionalFormDemoEntity = formDemoRepository.findById(id);

            if(!optionalFormDemoEntity.isPresent()){
                throw new AppException("Form Demo Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            formDemoRepository.deleteById(id);
            return formDemoMapper.toFormDemoDto(optionalFormDemoEntity.get());
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
