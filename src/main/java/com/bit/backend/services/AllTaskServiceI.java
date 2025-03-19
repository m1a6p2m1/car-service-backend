package com.bit.backend.services;

import com.bit.backend.dtos.AllTaskDto;

import java.util.List;

public interface AllTaskServiceI {
    AllTaskDto addAllTask(AllTaskDto allTaskDto);
    List<AllTaskDto> getData();
    AllTaskDto updateForm(long allTaskId, AllTaskDto allTaskDto);
}
