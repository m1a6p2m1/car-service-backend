package com.bit.backend.controllers;

import com.bit.backend.dtos.FormDemoDto;
import com.bit.backend.entities.FormDemoEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.FormDemoRepository;
import com.bit.backend.services.FormDemoServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class formDemoController {

    private final FormDemoServiceI formDemoServiceI;

    public formDemoController(FormDemoServiceI formDemoServiceI) {
        this.formDemoServiceI = formDemoServiceI;
    }

    @PostMapping("/form-demo")
    public ResponseEntity<FormDemoDto> addForm(@RequestBody FormDemoDto formDemoDto){
        try {
            FormDemoDto formDemoDtoResponse = formDemoServiceI.addFormDemoEntity(formDemoDto);
            return ResponseEntity.created(URI.create("/form-demo"+formDemoDtoResponse.getFirstName())).body(formDemoDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/form-demo")
    public ResponseEntity<List<FormDemoDto>> getData(){
        try {
            List<FormDemoDto> formDemoDtoList = formDemoServiceI.getData();
//            int i = 1/0;
            return ResponseEntity.ok(formDemoDtoList);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/form-demo/{id}")
    public ResponseEntity<FormDemoDto> updateForm(@PathVariable long id, @RequestBody FormDemoDto formDemoDto){
        try {
            FormDemoDto formDemoDtoResponse = formDemoServiceI.updateForm(id, formDemoDto);
            return ResponseEntity.ok(formDemoDtoResponse);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/form-demo/{id}")
    public ResponseEntity<FormDemoDto> deleteData(@PathVariable long id){
        try {
            FormDemoDto formDemoDto = formDemoServiceI.deleteData(id);
            return ResponseEntity.ok(formDemoDto);
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}






























