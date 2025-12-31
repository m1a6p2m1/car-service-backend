package com.bit.backend.controllers;

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
    public ResponseEntity<VehiclesDto> addVehiclesForm(@RequestBody VehiclesDto vehiclesDto){
        try{
            VehiclesDto vehiclesDtoReponse = vehiclesServiceI.addVehiclesEntity(vehiclesDto);
            return ResponseEntity.created(URI.create("/vehicles"+vehiclesDtoReponse.getId())).body(vehiclesDtoReponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehiclesDto>> getData(){
        try {
            List<VehiclesDto> vehiclesDtoList = vehiclesServiceI.getData();
            return ResponseEntity.ok(vehiclesDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehiclesDto> updateForm(@PathVariable long id, @RequestBody VehiclesDto vehiclesDto){
        try {
            VehiclesDto vehiclesDtoResponse = vehiclesServiceI.updateForm(id, vehiclesDto);
            return ResponseEntity.ok(vehiclesDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<VehiclesDto> deleteData(@PathVariable long id){
        try {
            VehiclesDto vehiclesDto = vehiclesServiceI.deleteData(id);
            return ResponseEntity.ok(vehiclesDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
