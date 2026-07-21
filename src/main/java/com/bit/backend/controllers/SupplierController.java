package com.bit.backend.controllers;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SupplierServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class SupplierController {
    public final SupplierServiceI supplierServiceI;

    public SupplierController(SupplierServiceI supplierServiceI) {this.supplierServiceI = supplierServiceI;}

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDto> addForm(@RequestBody SupplierDto supplierDto){
        try {
            SupplierDto supplierDtoResponse = supplierServiceI.addSupplierEntity(supplierDto);
            return ResponseEntity.ok(supplierDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<SupplierDto>> getData(){
        try {
            List<SupplierDto> supplierDtoList = supplierServiceI.getData();
            return ResponseEntity.ok(supplierDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/supplier/{supplierId}")
    public ResponseEntity<SupplierDto> updateForm(@PathVariable long supplierId, @RequestBody SupplierDto supplierDto){
        try {
            SupplierDto supplierDtoResponse = supplierServiceI.updateForm(supplierId,supplierDto);
            return ResponseEntity.ok(supplierDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<SupplierDto> deleteData(@PathVariable long supplierId){
        try {
            SupplierDto supplierDto = supplierServiceI.deleteData(supplierId);
            return ResponseEntity.ok(supplierDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

