package com.bit.backend.services.impl;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.entities.AppointmentEntity;
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
import java.util.Map;
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

                        savedDto.setCustomerName(user.getFirstName());
                        savedDto.setUniqueCusNo(user.getUniqueCusNo());
                        savedDto.setCustomerId(user.getId());

                        return savedDto;
                    }).toList();
            return savedList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public List<CustomerVehiclesDto> getData() {
////        System.out.println("******In DataBase**********");
//        try {
//            List<VehiclesEntity> vehiclesEntityList = vehiclesRepository.getVehiclesList();
//            List<CustomerVehiclesDto> vehiclesDtoList = vehiclesMapper.toCustomerVehiclesDtoList(vehiclesEntityList);
//            return vehiclesDtoList;
//        }catch (Exception e){
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public List<Map<String, Object>> getData() {
//        System.out.println(" Vehicle Service==============");
        return vehiclesRepository.getVehiclesList();
    }

    @Override
    public CustomerVehiclesDto updateForm(long id, CustomerVehiclesDto customerVehiclesDto) {

            VehiclesEntity existingVehicle = vehiclesRepository.findById(id)
                    .orElseThrow(() ->
                            new AppException("Vehicle Not Found", HttpStatus.NOT_FOUND)) ;

            existingVehicle.setLicencePlate(customerVehiclesDto.getLicencePlate());
            existingVehicle.setVehicleType(customerVehiclesDto.getVehicleType());
            existingVehicle.setVehicleModel(customerVehiclesDto.getVehicleModel());

            if (customerVehiclesDto.getCustomerId() != null) {

                User user = userRepository.findById(
                        customerVehiclesDto.getCustomerId()
                ).orElseThrow(() ->
                        new AppException("Customer Not Found",
                                HttpStatus.NOT_FOUND));

                existingVehicle.setUser(user);
            }

            VehiclesEntity vehiclesEntity = vehiclesRepository.save(existingVehicle);
            CustomerVehiclesDto vehiclesDtoResponse = vehiclesMapper.toCustomerVehiclesDto(vehiclesEntity);
            return vehiclesDtoResponse;

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

//    @Override
//    public List<CustomerVehiclesDto> getVehicleByCustomerId(Long customerId) {
//        return null;
//    }

    //Auto load licence_plate and vehicle_type when select customer_name
    @Override
    public List<CustomerVehiclesDto> getVehicleByUserId(Long customerId) {
        try {
            System.out.println(" Vehicle Service==============");
            List<VehiclesEntity> vehiclesList = vehiclesRepository.findByUserId(customerId);
            vehiclesList.forEach(v -> {
                System.out.println("Plate: " + v.getLicencePlate());
            });
            System.out.println("Vehicles List Size: " + vehiclesList.size());
            System.out.println("Vehicles: " + vehiclesList);
            return vehiclesMapper.toCustomerVehiclesDtoList(vehiclesList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CustomerVehiclesDto> getVehiclesByCusId(String uniqueCusNo) {
        System.out.println("******Customer Unique No**********");
        List<VehiclesEntity> vehicles = vehiclesRepository.findByUser_UniqueCusNo(uniqueCusNo);
        if (vehicles.isEmpty()) {
            return  List.of();
        }
        return vehiclesMapper.toCustomerVehiclesDtoList(vehicles);
    }

    //check license plate is already exist
    @Override
    public boolean isLicencePlateIsExists(String licencePlate){
        return vehiclesRepository.existsByLicencePlate(licencePlate);
    }
}
