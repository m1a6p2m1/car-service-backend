package com.bit.backend.services;

import com.bit.backend.dtos.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemServiceI {
    ItemDto addItemEntity(ItemDto itemDto);
    List<ItemDto> getData();
    ItemDto updateForm(long itemId, ItemDto itemDto);
    ItemDto deleteData(long itemId);
}
