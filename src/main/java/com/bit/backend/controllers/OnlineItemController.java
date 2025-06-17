package com.bit.backend.controllers;

import com.bit.backend.dtos.OnlineItemDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.OnlineItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class OnlineItemController {
    private final OnlineItemServiceI onlineItemServiceI;

    public OnlineItemController(OnlineItemServiceI onlineItemServiceI) {
        this.onlineItemServiceI = onlineItemServiceI;
    }

    @PostMapping(value = {"/online-item"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<OnlineItemDto> addForm(@RequestPart("itemForm") OnlineItemDto onlineItemDto, @RequestPart("image") MultipartFile file
    ){
        try {
            onlineItemDto.setImage(file.getBytes());
            onlineItemDto.setImageName(file.getOriginalFilename());
            onlineItemDto.setImageType(file.getContentType());

            OnlineItemDto onlineItemDtoResponse = onlineItemServiceI.addOnlineItemEntity(onlineItemDto);
            return ResponseEntity.created(URI.create("/online-item"+ onlineItemDtoResponse.getName())).body(onlineItemDtoResponse);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/online-item")
    public ResponseEntity<List<OnlineItemDto>> getData(){
        try {
            List<OnlineItemDto> onlineItemDtoList = onlineItemServiceI.getData();
            return ResponseEntity.ok(onlineItemDtoList);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/online-item/{id}")
    public ResponseEntity<OnlineItemDto> updateForm(@PathVariable long id, @RequestPart("itemForm") OnlineItemDto onlineItemDto, @RequestPart("image") MultipartFile file){
        try {
            onlineItemDto.setImage(file.getBytes());
            onlineItemDto.setImageName(file.getOriginalFilename());
            onlineItemDto.setImageType(file.getContentType());

            OnlineItemDto onlineItemDtoResponse = onlineItemServiceI.updateForm(id, onlineItemDto);
            return ResponseEntity.ok(onlineItemDtoResponse);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/online-item/{id}")
    public ResponseEntity<OnlineItemDto> deleteData(@PathVariable long id){
        try {
            OnlineItemDto onlineItemDto = onlineItemServiceI.deleteData(id);
            return ResponseEntity.ok(onlineItemDto);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
