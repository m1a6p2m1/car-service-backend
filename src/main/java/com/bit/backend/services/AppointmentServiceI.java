package com.bit.backend.services;

import com.bit.backend.dtos.AppointmentAssigneeChangeDto;
import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AppointmentServiceI {

    List<TimeSlotDto> getAvailableSlots(LocalDate date);

    AppointmentDto book(AppointmentDto dto);

    AppointmentDto editAppointment(long id, AppointmentDto appointmentDto);

    List<AppointmentDto> getAllAppointments();

    AppointmentAssigneeChangeDto changeAssignee(AppointmentAssigneeChangeDto appointmentAssigneeChangeDto);

    AppointmentDto deleteAppointment(long id);

    List<AppointmentDto> getAppointmentsByCusId(String uniqueCusNo);

    List<AppointmentDto> getAppointmentsByDateAndTime(LocalDate date, LocalTime time, String currentNo);

    AppointmentDto getAppointmentsByAppointmentNo(String appointmentUniqueNo);

    byte[] getBillPdf(Long id);

    void updatePaymentStatus(Long id);

    //service type report
    List<Map<String, Object>> getServiceTypesCount();
}
