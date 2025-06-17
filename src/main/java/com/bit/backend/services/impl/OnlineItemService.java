package com.bit.backend.services.impl;

import com.bit.backend.dtos.OnlineItemDto;
import com.bit.backend.entities.OnlineItemEntity;
import com.bit.backend.mappers.OnlineItemMapper;
import com.bit.backend.repositories.OnlineItemRepository;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.OnlineItemServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OnlineItemService implements OnlineItemServiceI {

    private final OnlineItemRepository onlineItemRepository;
    private final OnlineItemMapper onlineItemMapper;

    public OnlineItemService(OnlineItemRepository onlineItemRepository, OnlineItemMapper onlineItemMapper) {
        this.onlineItemRepository = onlineItemRepository;
        this.onlineItemMapper = onlineItemMapper;
    }

    @Override
    public OnlineItemDto addOnlineItemEntity(OnlineItemDto onlineItemDto) {
        System.out.println("***************In BackEnd--------------------");
        try {
            OnlineItemEntity onlineItemEntity = onlineItemMapper.toOnlineItemEntity(onlineItemDto);
            OnlineItemEntity savedItem = onlineItemRepository.save(onlineItemEntity);
            OnlineItemDto saveDto = onlineItemMapper.toOnlineItemDto(savedItem);
            return saveDto;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<OnlineItemDto> getData() {
//        System.out.println("***************In BackEnd--------------------");
        try {
            List<OnlineItemEntity> onlineItemEntityList = onlineItemRepository.findAll();
            List<OnlineItemDto> onlineItemDtoList = onlineItemMapper.toOnlineItemDtoList(onlineItemEntityList);
            return onlineItemDtoList;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OnlineItemDto updateForm(long id, OnlineItemDto onlineItemDto) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<OnlineItemEntity> optionalItemEntity = onlineItemRepository.findById(id);
            if (!optionalItemEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            OnlineItemEntity newOnlineItemEntity = onlineItemMapper.toOnlineItemEntity(onlineItemDto);
            newOnlineItemEntity.setId(id);
            OnlineItemEntity itemEntity = onlineItemRepository.save(newOnlineItemEntity);
            OnlineItemDto onlineItemDtoResponse = onlineItemMapper.toOnlineItemDto(itemEntity);
            return onlineItemDtoResponse;
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OnlineItemDto deleteData(long id) {
//        System.out.println("-------------------In BackEnd--------------------");
        try {
            Optional<OnlineItemEntity> optionalOnlineItemEntity = onlineItemRepository.findById(id);
            if (!optionalOnlineItemEntity.isPresent()){
                throw new AppException("Item Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            onlineItemRepository.deleteById(id);
            return onlineItemMapper.toOnlineItemDto(optionalOnlineItemEntity.get());
        }catch (Exception e){
            throw new AppException("Request fail with error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
