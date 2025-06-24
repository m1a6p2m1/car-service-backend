package com.bit.backend.services;

import com.bit.backend.dtos.OnlineItemDto;

import java.util.List;

public interface OnlineItemServiceI {
    OnlineItemDto addOnlineItemEntity(OnlineItemDto onlineItemDto);
    List<OnlineItemDto> getData();
    OnlineItemDto getItemById(Long id);
    OnlineItemDto updateForm(long id, OnlineItemDto onlineItemDto);
    OnlineItemDto deleteData(long id);
}
