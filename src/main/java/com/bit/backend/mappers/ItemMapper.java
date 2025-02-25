package com.bit.backend.mappers;

import com.bit.backend.dtos.ItemDto;
import com.bit.backend.entities.ItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ItemMapper {
    ItemDto toItemDto(ItemEntity itemEntity);
    ItemEntity toItemEntity(ItemDto itemDto);
    List<ItemDto> toItemDtoList(List<ItemEntity> itemEntityList);
}
