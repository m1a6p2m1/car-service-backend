package com.bit.backend.services.impl;

import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.entities.User;
import com.bit.backend.entities.VehiclesEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.VehiclesMapper;
import com.bit.backend.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public VehiclesService(VehiclesRepository vehiclesRepository, VehiclesMapper vehiclesMapper, UserRepository userRepository) {
        this.vehiclesRepository = vehiclesRepository;
        this.vehiclesMapper = vehiclesMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<CustomerVehiclesDto> addVehiclesEntity(VehiclesDto vehiclesDto) {
        try {
            User user = userRepository.findById(vehiclesDto.getCustomerId())
                    .orElseThrow(()->
                            new AppException("Customer Not Found", HttpStatus.BAD_REQUEST)
                    );
            List<CustomerVehiclesDto> savedList = vehiclesDto.getVehicles()
                    .stream()
                    .map(vehicleDto -> {
                        VehiclesEntity vehiclesEntity = vehiclesMapper.toVehiclesEntity(vehicleDto);
                        vehiclesEntity.setId(null); //ensure new insert
                        vehiclesEntity.setUser(user);

                        VehiclesEntity savedItem = vehiclesRepository.save(vehiclesEntity);
                        CustomerVehiclesDto savedDto = vehiclesMapper.toCustomerVehiclesDto(savedItem);

                        return savedDto;
                    }).toList();
            return savedList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerVehiclesDto> getData() {
//        System.out.println("******In DataBase**********");
        try {
            List<VehiclesEntity> vehiclesEntityList = vehiclesRepository.findAll();
            List<CustomerVehiclesDto> vehiclesDtoList = vehiclesMapper.toCustomerVehiclesDtoList(vehiclesEntityList);
            return vehiclesDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CustomerVehiclesDto updateForm(long id, CustomerVehiclesDto customerVehiclesDto) {
        try {
            Optional<VehiclesEntity> optionalVehiclesEntity = vehiclesRepository.findById(id);
            if (!optionalVehiclesEntity.isPresent()){
                throw new AppException("Vehicles Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            VehiclesEntity newVehiclesEntity = vehiclesMapper.toVehiclesEntity(customerVehiclesDto);
            newVehiclesEntity.setId(id);
            VehiclesEntity vehiclesEntity = vehiclesRepository.save(newVehiclesEntity);
            CustomerVehiclesDto vehiclesDtoResponse = vehiclesMapper.toCustomerVehiclesDto(vehiclesEntity);
            return vehiclesDtoResponse;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CustomerVehiclesDto deleteData(long id) {
        try {
            Optional<VehiclesEntity> optionalVehiclesEntity = vehiclesRepository.findById(id);
            if (!optionalVehiclesEntity.isPresent()){
                throw new AppException("Vehicles Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            vehiclesRepository.deleteById(id);
            return vehiclesMapper.toCustomerVehiclesDto(optionalVehiclesEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerVehiclesDto> getVehicleByCustomerId(Long customerId) {
        return null;
    }

    //Auto load licence_plate and vehicle_type when select customer_name
//    @Override
//    public List<VehiclesDto> getVehicleByUserId(Long userId) {
//        try {
//            List<VehiclesEntity> vehiclesList = vehiclesRepository.findByUserId(userId);
//            return vehiclesList.stream()
//                    .map(vehiclesMapper::toCustomerVehiclesDto)
//                    .toList();
//        } catch (Exception e) {
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
