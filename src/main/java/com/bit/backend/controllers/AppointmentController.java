package com.bit.backend.controllers;

import com.bit.backend.dtos.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
        System.out.println("************************controller hit********************");
        return ResponseEntity.ok(appointmentServiceI.book(appointmentDto));
    }

    @PutMapping("/edit-appointments/{id}")
    public ResponseEntity<AppointmentDto> editAppointment(@PathVariable long id, @RequestBody AppointmentDto appointmentDto) {
        System.out.println("************************ edit appointment controller hit********************");
        return ResponseEntity.ok(appointmentServiceI.editAppointment(id, appointmentDto));
    }

//    @GetMapping("/appointment-service/by-date-time")
//    public ResponseEntity<List<AppointmentDto>> getByDataAndTime(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
////        System.out.println("************************appointment book********************");
////        LocalDate localDate = LocalDate.parse(date);
////        LocalTime localTime = LocalTime.parse(time);
//
//        return ResponseEntity.ok(appointmentServiceI.getAppointmentsByDateAndTime(date, time, current));
//    }

    @GetMapping("/appointment-service/by-date-time")
    public ResponseEntity<List<AppointmentDto>> getByDataAndTime(
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam(required = false) String currentNo) {

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        return ResponseEntity.ok(
                appointmentServiceI.getAppointmentsByDateAndTime(localDate, localTime, currentNo)
        );
    }

    @GetMapping("/appointment-service/{appointmentUniqueNo}")
    public ResponseEntity<AppointmentDto> getDetailsByAppointmentNo(@PathVariable String appointmentUniqueNo){
        try {
            System.out.println("Controller reached: " + appointmentUniqueNo);
            AppointmentDto appointmentDetails = appointmentServiceI.getAppointmentsByAppointmentNo(appointmentUniqueNo);
            return ResponseEntity.ok(appointmentDetails);
        } catch (Exception e) {
            throw new AppException("Failed to get appointment details " + appointmentUniqueNo + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/appointment/{id}/bill")
    public ResponseEntity<byte[]> viewBill(@PathVariable Long id) {

        byte[] pdf = appointmentServiceI.getBillPdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=bill.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    //update the appointment form status field when customer made the payment
    @PutMapping("/all-appointments/payment-done/{id}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long id) {
        appointmentServiceI.updatePaymentStatus(id);
        return ResponseEntity.ok("Payment Status update Successfully");
    }
}
