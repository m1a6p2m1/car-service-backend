package com.bit.backend.services;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentServiceI {

    List<TimeSlotDto> getAvailableSlots(LocalDate date);

    AppointmentDto book(AppointmentDto dto);

    List<AppointmentDto> getAllAppointments();
}
