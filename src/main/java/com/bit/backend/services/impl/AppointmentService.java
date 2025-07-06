package com.bit.backend.services.impl;

import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.entities.AppointmentEntity;
import com.bit.backend.mappers.AppointmentMapper;
import com.bit.backend.repositories.AppointmentRepository;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AppointmentService implements AppointmentServiceI {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    private static final LocalTime OPEN = LocalTime.of(9, 0);
    private static final LocalTime CLOSE_WEEKDAY = LocalTime.of(17, 0);
    private static final LocalTime CLOSE_SATURDAY = LocalTime.of(15, 0);
    private static final LocalTime LUNCH_START = LocalTime.of(13, 0);
    private static final LocalTime LUNCH_END = LocalTime.of(14, 0);
    private static final int SLOT_MINUTES = 60;
    private static final int MAX_BAYS = 3;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<TimeSlotDto> getAvailableSlots(LocalDate date) {
        // Sunday is closed
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) return List.of();

        // 1) generate all slots for that date
        List<LocalTime> allSlots = getSlotsForDay(date);

        // 2) get counts already booked
        Map<String, Long> counts = appointmentRepository.countPerTimeSlot(date).stream()
                .collect(Collectors.toMap(
                        o -> ((LocalTime) o[0]).toString(),
                        o -> (Long) o[1]));
        System.out.println("***************appointment getSlots service*********************");
        // 3) build DTO list
        return allSlots.stream()
                .map(t -> new TimeSlotDto(t.toString(), counts.getOrDefault(t.toString(), 0L)))
                .toList();
    }

    private List<LocalTime> getSlotsForDay(LocalDate date) {
//        LocalTime close = date.getDayOfWeek() == DayOfWeek.SATURDAY ? CLOSE_SATURDAY : CLOSE_WEEKDAY;
        List<LocalTime> result = new ArrayList<>();
        DayOfWeek day = date.getDayOfWeek();

        LocalTime open = OPEN;
        LocalTime close;

        boolean skipLunch = false;

        if (day == DayOfWeek.SATURDAY) {
            close = CLOSE_SATURDAY;
            skipLunch = true; // no Lunch break on saturday
        }else {
            close = CLOSE_WEEKDAY;
        }
        for (LocalTime t = OPEN; t.isBefore(close); t = t.plusMinutes(SLOT_MINUTES)) {
            if (!skipLunch && t.compareTo(LUNCH_START) >= 0 && t.isBefore(LUNCH_END)) continue; // skip lunch
            result.add(t);
        }
        return result;
    }

    @Override
    public AppointmentDto book(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.appointmentDate();
        LocalTime slot = appointmentDto.timeSlot();

        // find a free bay 1‑3
        int bay = IntStream.rangeClosed(1, MAX_BAYS)
                .filter(b -> appointmentRepository.findByAppointmentDateAndTimeSlotAndBay(date, slot, b).isEmpty())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All bays full for that slot"));

        AppointmentEntity saved = appointmentRepository.save(
                new AppointmentEntity(date, slot, bay));
        System.out.println("************************appointment book service********************");
        return appointmentMapper.toAppointmentDto(saved);
    }


}
