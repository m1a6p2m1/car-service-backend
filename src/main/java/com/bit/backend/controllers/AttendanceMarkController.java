package com.bit.backend.controllers;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AttendanceMarkServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class AttendanceMarkController {
    private final AttendanceMarkServiceI attendanceMarkServiceI;

    public AttendanceMarkController(AttendanceMarkServiceI attendanceMarkServiceI) {
        this.attendanceMarkServiceI = attendanceMarkServiceI;
    }

    @PostMapping("/attendance-mark")
    public ResponseEntity<AttendanceMarkDto> addForm(@RequestBody AttendanceMarkDto attendanceMarkDto){
        try {
            AttendanceMarkDto attendanceMarkDtoResponse = attendanceMarkServiceI.addAttendanceMarkEntity(attendanceMarkDto);
            return ResponseEntity.created(URI.create("/attendance-mark"+attendanceMarkDtoResponse.getEmpId())).body(attendanceMarkDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/attendance-mark")
    public ResponseEntity<List<AttendanceMarkDto>> getData(){
        try {
            List<AttendanceMarkDto> attendanceMarkDtoList = attendanceMarkServiceI.getData();
            return ResponseEntity.ok(attendanceMarkDtoList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
