package com.bit.backend.controllers;

import com.bit.backend.dtos.CustomerVehiclesDto;
import com.bit.backend.dtos.VehiclesDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.VehiclesServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class VehiclesController {
    private final VehiclesServiceI vehiclesServiceI;

    public VehiclesController(VehiclesServiceI vehiclesServiceI) {
        this.vehiclesServiceI = vehiclesServiceI;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<List<CustomerVehiclesDto>> addVehiclesForm(@RequestBody VehiclesDto vehiclesDto){
        try{
            List<CustomerVehiclesDto> savedVehicles = vehiclesServiceI.addVehiclesEntity(vehiclesDto);
            return ResponseEntity.ok(savedVehicles);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<CustomerVehiclesDto>> getData(){
        try {
            List<CustomerVehiclesDto> vehiclesDtoList = vehiclesServiceI.getData();
            return ResponseEntity.ok(vehiclesDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{customerId}")
//    public ResponseEntity<List<CustomerVehiclesDto>> getByCustomer(
//            @PathVariable Long customerId) {
//
//        return ResponseEntity.ok(vehiclesServiceI.getVehiclesByCustomer(customerId));
//    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<CustomerVehiclesDto> updateForm(@PathVariable long id, @RequestBody CustomerVehiclesDto customerVehiclesDto){
        try {
            CustomerVehiclesDto vehiclesDtoResponse = vehiclesServiceI.updateForm(id, customerVehiclesDto);
            return ResponseEntity.ok(vehiclesDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<CustomerVehiclesDto> deleteData(@PathVariable long id){
        try {
            CustomerVehiclesDto vehiclesDto = vehiclesServiceI.deleteData(id);
            return ResponseEntity.ok(vehiclesDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Auto load licence_plate and vehicle_type when select customer_name
//    @GetMapping("/register/vehicles/{customerId}")
//    public ResponseEntity<List<VehiclesDto>> getVehicleByUser(@PathVariable Long UserId) {
//
//        try {
//            List<VehiclesDto> vehicle = vehiclesServiceI.getVehicleByUserId(UserId);
//            return ResponseEntity.ok(vehicle);
//
//        } catch (Exception e) {
//            throw new AppException(
//                    "Request Failed With Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
}
