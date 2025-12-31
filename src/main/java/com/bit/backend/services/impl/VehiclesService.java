package com.bit.backend.services.impl;

import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.entities.VehiclesEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.VehiclesMapper;
import com.bit.backend.repositories.VehiclesRepository;
import com.bit.backend.services.VehiclesServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesService implements VehiclesServiceI {
    private final VehiclesRepository vehiclesRepository;
    private final VehiclesMapper vehiclesMapper;

    public VehiclesService(VehiclesRepository vehiclesRepository, VehiclesMapper vehiclesMapper) {
        this.vehiclesRepository = vehiclesRepository;
        this.vehiclesMapper = vehiclesMapper;
    }


    @Override
    public VehiclesDto addVehiclesEntity(VehiclesDto vehiclesDto) {
        try {
            VehiclesEntity vehiclesEntity = vehiclesMapper.toVehiclesEntity(vehiclesDto);
            VehiclesEntity savedItem = vehiclesRepository.save(vehiclesEntity);
            VehiclesDto savedDto = vehiclesMapper.toVehiclesDto(savedItem);

            return savedDto;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<VehiclesDto> getData() {
//        System.out.println("******In DataBase**********");
        try {
            List<VehiclesEntity> vehiclesEntityList = vehiclesRepository.findAll();
            List<VehiclesDto> vehiclesDtoList = vehiclesMapper.toVehiclesDtoList(vehiclesEntityList);
            return vehiclesDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public VehiclesDto updateForm(long id, VehiclesDto vehiclesDto) {
        try {
            Optional<VehiclesEntity> optionalVehiclesEntity = vehiclesRepository.findById(id);
            if (!optionalVehiclesEntity.isPresent()){
                throw new AppException("Vehicles Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            VehiclesEntity newVehiclesEntity = vehiclesMapper.toVehiclesEntity(vehiclesDto);
            newVehiclesEntity.setId(id);
            VehiclesEntity vehiclesEntity = vehiclesRepository.save(newVehiclesEntity);
            VehiclesDto vehiclesDtoResponse = vehiclesMapper.toVehiclesDto(vehiclesEntity);
            return vehiclesDtoResponse;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public VehiclesDto deleteData(long id) {
        try {
            Optional<VehiclesEntity> optionalVehiclesEntity = vehiclesRepository.findById(id);
            if (!optionalVehiclesEntity.isPresent()){
                throw new AppException("Vehicles Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            vehiclesRepository.deleteById(id);
            return vehiclesMapper.toVehiclesDto(optionalVehiclesEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
