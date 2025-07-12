package com.bit.backend.controllers;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/get-all-appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentServiceI.getAllAppointments());
    }
}
