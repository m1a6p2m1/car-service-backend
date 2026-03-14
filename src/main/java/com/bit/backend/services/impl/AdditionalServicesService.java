package com.bit.backend.services.impl;

import com.bit.backend.dtos.AdditionalServicesDto;
import com.bit.backend.entities.AdditionalServicesEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AdditionalServicesMapper;
import com.bit.backend.repositories.AdditionalServicesRepository;
import com.bit.backend.services.AdditionalServicesServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalServicesService implements AdditionalServicesServiceI {
    private final AdditionalServicesMapper additionalServicesMapper;
    private final AdditionalServicesRepository additionalServicesRepository;

    public AdditionalServicesService(AdditionalServicesMapper additionalServicesMapper, AdditionalServicesRepository additionalServicesRepository) {
        this.additionalServicesMapper = additionalServicesMapper;
        this.additionalServicesRepository = additionalServicesRepository;
    }

    @Override
    public AdditionalServicesDto addData(AdditionalServicesDto additionalServicesDto) {
//        System.out.println("***************In BackEnd--------------------");
        try {
            AdditionalServicesEntity Entity = additionalServicesMapper.toEntity(additionalServicesDto);
            AdditionalServicesEntity savedItem = additionalServicesRepository.save(Entity);
            AdditionalServicesDto saveDto = additionalServicesMapper.toDto(savedItem);
            return saveDto;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AdditionalServicesDto> getData() {
//        System.out.println("***************In BackEnd--------------------");
        try {
            List<AdditionalServicesEntity> EntityList = additionalServicesRepository.findAll();
            List<AdditionalServicesDto> itemDtoList = additionalServicesMapper.toDtoList(EntityList);
            return itemDtoList;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AdditionalServicesDto updateForm(long id, AdditionalServicesDto additionalServicesDto) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<AdditionalServicesEntity> optionalItemEntity = additionalServicesRepository.findById(id);
            if (!optionalItemEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            AdditionalServicesEntity newItemEntity = additionalServicesMapper.toEntity(additionalServicesDto);
            newItemEntity.setId(id);
            AdditionalServicesEntity itemEntity = additionalServicesRepository.save(newItemEntity);
            AdditionalServicesDto itemDtoResponse = additionalServicesMapper.toDto(itemEntity);
            return itemDtoResponse;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AdditionalServicesDto deleteData(long id) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<AdditionalServicesEntity> optionalEntity = additionalServicesRepository.findById(id);
            if (!optionalEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            additionalServicesRepository.deleteById(id);
            return additionalServicesMapper.toDto(optionalEntity.get());
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
