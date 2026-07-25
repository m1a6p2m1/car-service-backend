package com.bit.backend.services.impl;

import com.bit.backend.dtos.SupplierDto;
import com.bit.backend.entities.SupplierEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SupplierMapper;
import com.bit.backend.repositories.SupplierRepository;
import com.bit.backend.services.SupplierServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements SupplierServiceI {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }


    @Override
    public SupplierDto addSupplierEntity(SupplierDto supplierDto) {
//        System.out.println("************In BackEnd************");
        try {
            SupplierEntity supplierEntity = supplierMapper.toSupplierEntity(supplierDto);
            SupplierEntity savedItem = supplierRepository.save(supplierEntity);
            SupplierDto savedDto = supplierMapper.toSupplierDto(supplierEntity);
            return savedDto;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<SupplierDto> getData() {
//        System.out.println("----------------In Back End---------------------");
        try {
            List<SupplierEntity> supplierEntityList = supplierRepository.findAll();
            List<SupplierDto> supplierDtoList = supplierMapper.toSupplierDtoList(supplierEntityList);
            return supplierDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplierDto updateForm(long supplierId, SupplierDto supplierDto) {
//        System.out.println("----------------In Back End---------------------");
        try {
            Optional<SupplierEntity> optionalSupplierEntity = supplierRepository.findById(supplierId);
            if (!optionalSupplierEntity.isPresent()){
                throw new AppException("Supplier Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            SupplierEntity newSupplierEntity = supplierMapper.toSupplierEntity(supplierDto);
            newSupplierEntity.setSupplierId(supplierId);
            SupplierEntity supplierEntity = supplierRepository.save(newSupplierEntity);
            SupplierDto supplierDtoResponse = supplierMapper.toSupplierDto(supplierEntity);
            return supplierDtoResponse;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplierDto deleteData(long supplierId) {
//        System.out.println("----------------In Back End---------------------");
        try {
            Optional<SupplierEntity> optionalSupplierEntity = supplierRepository.findById(supplierId);
            if (!optionalSupplierEntity.isPresent()){
                throw new AppException("Supplier Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            supplierRepository.deleteById(supplierId);
            return supplierMapper.toSupplierDto(optionalSupplierEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //check phoneNumber is already exist
    @Override
    public boolean isPhoneNumberIsExists(String phoneNumber){
        return supplierRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isNicIsExists(String nic){
        return supplierRepository.existsByNic(nic);
    }
}
