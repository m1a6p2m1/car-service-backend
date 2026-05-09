package com.bit.backend.services;

import com.bit.backend.dtos.AppointmentAssigneeChangeDto;
import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentServiceI {

    List<TimeSlotDto> getAvailableSlots(LocalDate date);

    AppointmentDto book(AppointmentDto dto);

    List<AppointmentDto> getAllAppointments();

    AppointmentAssigneeChangeDto changeAssignee(AppointmentAssigneeChangeDto appointmentAssigneeChangeDto);

    AppointmentDto deleteAppointment(long id);

    List<AppointmentDto> getAppointmentsByCusId(String uniqueCusNo);

    List<AppointmentDto> getAppointmentsByDateAndTime(LocalDate date, LocalTime time, String currentNo);

    AppointmentDto getAppointmentsByAppointmentNo(String appointmentUniqueNo);
}
