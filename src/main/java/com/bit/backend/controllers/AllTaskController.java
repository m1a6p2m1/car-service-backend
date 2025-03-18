package com.bit.backend.controllers;

import com.bit.backend.dtos.AllTaskDto;
import com.bit.backend.services.AllTaskServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AllTaskController {
    private final AllTaskServiceI allTaskServiceI;

    public AllTaskController(AllTaskServiceI allTaskServiceI) {
        this.allTaskServiceI = allTaskServiceI;
    }
    @PostMapping("/all-task")
    public ResponseEntity<AllTaskDto> addForm(@RequestBody AllTaskDto allTaskDto){
        AllTaskDto allTaskDtoResponse = allTaskServiceI.addAllTask(allTaskDto);
        return ResponseEntity.created(URI.create("/all-task"+ allTaskDtoResponse.getInProgressTasks())).body(allTaskDtoResponse);
    }

    @GetMapping("/all-task")
    public ResponseEntity<List<AllTaskDto>> getData(){
        List<AllTaskDto> allTaskDtoList = allTaskServiceI.getData();
        return ResponseEntity.ok(allTaskDtoList);
    }

    @PutMapping("/all-task/{allTaskId}")
    public ResponseEntity<AllTaskDto> updateForm(@PathVariable long allTaskId, @RequestBody AllTaskDto allTaskDto ){
        AllTaskDto allTaskDtoResponse = allTaskServiceI.updateForm(allTaskId, allTaskDto);
        return ResponseEntity.ok(allTaskDtoResponse);
    }
}
