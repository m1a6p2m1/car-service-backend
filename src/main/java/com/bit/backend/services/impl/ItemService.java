package com.bit.backend.services.impl;

import com.bit.backend.dtos.ItemDto;
import com.bit.backend.entities.ItemEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ItemMapper;
import com.bit.backend.repositories.ItemRepository;
import com.bit.backend.services.ItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements ItemServiceI {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemDto addItemEntity(ItemDto itemDto) {
//        System.out.println("***************In BackEnd--------------------");
        try {
            ItemEntity itemEntity = itemMapper.toItemEntity(itemDto);
            ItemEntity savedItem = itemRepository.save(itemEntity);
            ItemDto saveDto = itemMapper.toItemDto(savedItem);
            return saveDto;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ItemDto> getData() {
//        System.out.println("***************In BackEnd--------------------");
        try {
            List<ItemEntity> itemEntityList = itemRepository.findAll();
            List<ItemDto> itemDtoList = itemMapper.toItemDtoList(itemEntityList);
            return itemDtoList;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ItemDto updateForm(long itemId, ItemDto itemDto) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);
            if (!optionalItemEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            ItemEntity newItemEntity = itemMapper.toItemEntity(itemDto);
            newItemEntity.setItemId(itemId);
            ItemEntity itemEntity = itemRepository.save(newItemEntity);
            ItemDto itemDtoResponse = itemMapper.toItemDto(itemEntity);
            return itemDtoResponse;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ItemDto deleteData(long itemId) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);
            if (!optionalItemEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            itemRepository.deleteById(itemId);
            return itemMapper.toItemDto(optionalItemEntity.get());
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
