package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime timeSlot;
    private Integer bay;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, LocalDate appointmentDate, LocalTime timeSlot, Integer bay) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.bay = bay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }


    public LocalDate appointmentDate() {
        return null;
    }

    public LocalTime timeSlot() {
        return null;
    }
}
