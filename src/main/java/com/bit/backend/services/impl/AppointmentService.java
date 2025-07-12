package com.bit.backend.services.impl;

import com.bit.backend.dtos.AppointmentAssigneeChangeDto;
import com.bit.backend.dtos.AppointmentDto;
import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.entities.AppointmentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AppointmentMapper;
import com.bit.backend.repositories.AppointmentRepository;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        LocalDate date = appointmentDto.getDate();
        LocalTime slot = appointmentDto.getTime();
//        long id = appointmentDto.getId();
        String vehicleType = appointmentDto.getVehicleType();
        String serviceType = appointmentDto.getServiceType();
        String taskName = appointmentDto.getTaskName();
        String additionalServices = appointmentDto.getAdditionalServices();
        String customerName = appointmentDto.getCustomerName();
        String email = appointmentDto.getEmail();
        String phoneNumber = appointmentDto.getPhoneNumber();
        Double totalPrice = appointmentDto.getTotalServicePrice();


        // find a free bay 1‑3
        int bay = IntStream.rangeClosed(1, MAX_BAYS)
                .filter(b -> appointmentRepository.findByDateAndTimeAndBay(date, slot, b).isEmpty())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All bays full for that slot"));

        AppointmentEntity appointmentEntity = appointmentMapper.toAppointmentEntity(appointmentDto);

        AppointmentEntity saved = appointmentRepository.save(appointmentEntity);
//        AppointmentEntity saved = appointmentRepository.save(new AppointmentEntity(id, date, slot, bay, vehicleType, serviceType, taskName, additionalServices, customerName, email, phoneNumber, totalPrice));

        System.out.println(" Appointment saved: " + saved.getDate() + " " + saved.getTime() + " Bay: " + saved.getBay());
        System.out.println("************************appointment book service********************");
        // Recount how many appointments are now booked for this slot
        long bookedCount = appointmentRepository.countByDateAndTime(date, slot);

        // Return DTO with updated data
        return new AppointmentDto(
                saved.getId(),
                saved.getDate(),
                saved.getTime(),
                saved.getBay(),
                bookedCount,
                saved.getTaskName(),
                saved.getVehicleType(),
                saved.getServiceType(),
                saved.getAdditionalServices(),
                saved.getCustomerName(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getTotalServicePrice(),
                saved.getAssignee(),
                saved.getAssigneeName()
        );
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentMapper.toAppointmentDtoList(appointmentRepository.findAll());
    }

    @Override
    public AppointmentAssigneeChangeDto changeAssignee(AppointmentAssigneeChangeDto appointmentAssigneeChangeDto) {
        try {
            Optional<AppointmentEntity> oAppointmentEntity = appointmentRepository.findById(appointmentAssigneeChangeDto.getId());

            if (!oAppointmentEntity.isPresent()) {
                throw  new AppException("Appointment not exists", HttpStatus.BAD_REQUEST);
            }

            AppointmentEntity oldAppointmentEntity = oAppointmentEntity.get();
            oldAppointmentEntity.setAssignee(appointmentAssigneeChangeDto.getAssignee());
            oldAppointmentEntity.setAssigneeName(appointmentAssigneeChangeDto.getAssigneeName());
            AppointmentEntity savedEntity = appointmentRepository.save(oldAppointmentEntity);
            return appointmentAssigneeChangeDto;
        } catch (Exception e) {
            throw  new AppException("Error Occured: Please try again!", HttpStatus.BAD_REQUEST);
        }
    }
}
