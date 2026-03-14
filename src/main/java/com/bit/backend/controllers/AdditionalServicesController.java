package com.bit.backend.controllers;

import com.bit.backend.dtos.AdditionalServicesDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AdditionalServicesServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AdditionalServicesController {
    private final AdditionalServicesServiceI additionalServicesServiceI;

    public AdditionalServicesController(AdditionalServicesServiceI additionalServicesServiceI) {
        this.additionalServicesServiceI = additionalServicesServiceI;
    }


    @PostMapping("/additional-services")
    public ResponseEntity<AdditionalServicesDto> addTask(@RequestBody AdditionalServicesDto additionalServicesDto){
        AdditionalServicesDto dtoResponse = additionalServicesServiceI.addData(additionalServicesDto);
        return ResponseEntity.created(URI.create("/additional-services"+dtoResponse.getAdditionalServicesName())).body(dtoResponse);
    }

    @GetMapping("/additional-services")
    public ResponseEntity<List<AdditionalServicesDto>> getData(){
        try {
            List<AdditionalServicesDto> DtoList = additionalServicesServiceI.getData();
            return ResponseEntity.ok(DtoList);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/additional-services/{id}")
    public ResponseEntity<AdditionalServicesDto> updateForm(@PathVariable long id,@RequestBody AdditionalServicesDto additionalServicesDto){
        try {
            AdditionalServicesDto DtoResponse = additionalServicesServiceI.updateForm(id, additionalServicesDto);
            return ResponseEntity.ok(DtoResponse);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/additional-services/{id}")
    public ResponseEntity<AdditionalServicesDto> deleteData(@PathVariable long id){
        try {
            AdditionalServicesDto additionalServicesDto = additionalServicesServiceI.deleteData(id);
            return ResponseEntity.ok(additionalServicesDto);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
