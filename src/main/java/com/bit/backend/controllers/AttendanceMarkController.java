package com.bit.backend.controllers;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.dtos.AttendanceMarkEmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
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
import java.util.Map;

@RestController
public class AttendanceMarkController {
    private final AttendanceMarkServiceI attendanceMarkServiceI;

    public AttendanceMarkController(AttendanceMarkServiceI attendanceMarkServiceI) {
        this.attendanceMarkServiceI = attendanceMarkServiceI;
    }

    @PostMapping("/attendance-mark/save")
    public ResponseEntity<List<AttendanceMarkDto>> addForm(@RequestBody List<AttendanceMarkDto> attendanceList){
        try {
            List<AttendanceMarkDto> attendanceMarkDtoResponse = attendanceMarkServiceI.saveAttendance(attendanceList);
            return ResponseEntity.ok(attendanceMarkDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/attendance-mark")
//    public ResponseEntity<List<AttendanceMarkEmployeeDto>> getData(){
//        try {
//            List<AttendanceMarkEmployeeDto> attendanceMarkDtoList = attendanceMarkServiceI.getData();
//            return ResponseEntity.ok(attendanceMarkDtoList);
//        }catch (Exception e){
//            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/attendance-mark/active-employee")
    public ResponseEntity<List<Map<String, Object>>> getActiveEmployees(){
        try {
            List<Map<String, Object>> activeEmployeeList = attendanceMarkServiceI.getActiveEmployees();
            return ResponseEntity.ok(activeEmployeeList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
