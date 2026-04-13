package com.bit.backend.controllers;

import com.bit.backend.dtos.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentServiceI appointmentServiceI;

    public AppointmentController(AppointmentServiceI appointmentServiceI) {
        this.appointmentServiceI = appointmentServiceI;
    }

    @GetMapping("/appointment-service/available-slots")
    public ResponseEntity<List<TimeSlotDto>> getSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        System.out.println("***************appointment getSlots*********************");
        return ResponseEntity.ok(appointmentServiceI.getAvailableSlots(date));
    }

    @PostMapping("/appointment-service")
    public ResponseEntity<AppointmentDto> book(@RequestBody AppointmentDto appointmentDto) {
        System.out.println("************************appointment book********************");
        return ResponseEntity.ok(appointmentServiceI.book(appointmentDto));
    }

    @GetMapping("/appointment-service/get-all-appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentServiceI.getAllAppointments());
    }

    @PutMapping("/change-appointment-assignee")
    public ResponseEntity<AppointmentAssigneeChangeDto> changeAssignee(@RequestBody AppointmentAssigneeChangeDto appointmentAssigneeChangeDto){
        try {
            AppointmentAssigneeChangeDto appointmentAssigneeChangeDtoResponse = appointmentServiceI.changeAssignee(appointmentAssigneeChangeDto);
            return ResponseEntity.ok(appointmentAssigneeChangeDtoResponse);
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/appointment-service/get-all-appointments/{uniqueCusNo}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByCusId(@PathVariable String uniqueCusNo) {
        try {
            System.out.println("Controller reached: " + uniqueCusNo);
            List<AppointmentDto> appointmentDtoList = appointmentServiceI.getAppointmentsByCusId(uniqueCusNo);
            return ResponseEntity.ok(appointmentDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to get employee with ID " + uniqueCusNo + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all-appointments/{id}")
    public ResponseEntity<AppointmentDto> deleteAppointment(@PathVariable long id){
        try {
            AppointmentDto appointmentDto = appointmentServiceI.deleteAppointment(id);
            return ResponseEntity.ok(appointmentDto);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
