package com.bit.backend.mappers;

import com.bit.backend.dtos.OnlineItemDto;
import com.bit.backend.entities.OnlineItemEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface OnlineItemMapper {
    OnlineItemDto toOnlineItemDto(OnlineItemEntity onlineItemEntity);
    OnlineItemEntity toOnlineItemEntity(OnlineItemDto onlineItemDto);
    List<OnlineItemDto> toOnlineItemDtoList(List<OnlineItemEntity> onlineItemEntityList);
}
