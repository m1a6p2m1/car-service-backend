package com.bit.backend.controllers;

import com.bit.backend.dtos.ItemDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.ItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class ItemController {
    private final ItemServiceI itemServiceI;

    public ItemController(ItemServiceI itemServiceI) {
        this.itemServiceI = itemServiceI;
    }

    @PostMapping("/item")
    public ResponseEntity<ItemDto> addForm(
            @RequestParam("itemCode") String itemCode,//<------
            @RequestParam("itemName") String itemName,
            @RequestParam("itemCategory") String itemCategory,
            @RequestParam("supplierName") String supplierName,
            @RequestParam("brandName") String brandName,
            @RequestParam("description") String description,
            @RequestParam(value = "itemImage", required = false) MultipartFile itemImage
    ){
        try {

            ItemDto itemDto = new ItemDto();//<------
            itemDto.setItemCode(itemCode);
            itemDto.setItemName(itemName);
            itemDto.setItemCategory(itemCategory);
            itemDto.setSupplierName(supplierName);
            itemDto.setBrandName(brandName);
            itemDto.setDescription(description);

            ItemDto itemDtoResponse = itemServiceI.addItemEntity(itemDto);
            return ResponseEntity.created(URI.create("/item"+ itemDtoResponse.getItemName())).body(itemDtoResponse);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item")
    public ResponseEntity<List<ItemDto>> getData(){
        try {
            List<ItemDto> itemDtoList = itemServiceI.getData();
            return ResponseEntity.ok(itemDtoList);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/item/{itemId}")
    public ResponseEntity<ItemDto> updateForm(@PathVariable long itemId, @RequestBody ItemDto itemDto){
        try {
            ItemDto itemDtoResponse = itemServiceI.updateForm(itemId, itemDto);
            return ResponseEntity.ok(itemDtoResponse);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<ItemDto> deleteData(@PathVariable long itemId){
        try {
            ItemDto itemDto = itemServiceI.deleteData(itemId);
            return ResponseEntity.ok(itemDto);
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
