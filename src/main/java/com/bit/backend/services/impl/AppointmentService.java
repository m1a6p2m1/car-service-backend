package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AppointmentMapper;
import com.bit.backend.repositories.AppointmentRepository;
import com.bit.backend.repositories.DefinedTasksRepository;
import com.bit.backend.repositories.TaskRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.AppointmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AppointmentService implements AppointmentServiceI {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final UserRepository userRepository;
    private final DefinedTasksRepository definedTasksRepository;

    private static final LocalTime OPEN = LocalTime.of(9, 0);
    private static final LocalTime CLOSE_WEEKDAY = LocalTime.of(18, 0);
    private static final LocalTime CLOSE_SATURDAY = LocalTime.of(16, 0);
    private static final LocalTime LUNCH_START = LocalTime.of(13, 0);
    private static final LocalTime LUNCH_END = LocalTime.of(14, 0);
    private static final int SLOT_MINUTES = 60;
    private static final int MAX_BAYS = 3;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, UserRepository userRepository, DefinedTasksRepository definedTasksRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.userRepository = userRepository;
        this.definedTasksRepository = definedTasksRepository;
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
            skipLunch = false; // Lunch break on saturday
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
        String login = appointmentDto.getLogin();

        if (login == null || login.isEmpty()) {
            throw new RuntimeException("Login is missing in request");
        }

        // Find user from DB
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // decide role
        String roleToSave;
        if ("EMPLOYEE".equalsIgnoreCase(user.getRole())) {
            roleToSave = "SYSTEM";
        } else {
            roleToSave = "CUSTOMER";
        }

        // find a free bay 1‑3
        int bay = IntStream.rangeClosed(1, MAX_BAYS)
                .filter(b -> appointmentRepository.findByDateAndTimeAndBay(date, slot, b).isEmpty())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All bays full for that slot"));

        AppointmentEntity appointmentEntity = appointmentMapper.toAppointmentEntity(appointmentDto);

        if (appointmentDto.getTaskId() == null) {
            throw new RuntimeException("Task ID is required");
        }

        DefinedTasksEntity task = definedTasksRepository.findById(appointmentDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        appointmentEntity.setDefinedTasks(task);

        // set role
        appointmentEntity.setRole(roleToSave);
        appointmentEntity.setBay(bay);
        appointmentEntity.setUser(user);

        AppointmentEntity saved = appointmentRepository.save(appointmentEntity);

        //Generate Unique No
        String uniqueNo = generateAppointmentNumber(saved);
        saved.setAppointmentUniqueNo(uniqueNo);
        //Save again with unique No
        saved = appointmentRepository.save(saved);
//
        // Recount how many appointments are now booked for this slot
        long bookedCount = appointmentRepository.countByDateAndTime(date, slot);

        // Return DTO with updated data
        return new AppointmentDto(
                saved.getId(),
                saved.getAppointmentUniqueNo(),
                saved.getDate(),
                saved.getTime(),
                saved.getBay(),
                saved.getStatus(),
                bookedCount,
                saved.getDefinedTasks() != null ? saved.getDefinedTasks().getId(): null,
                saved.getDefinedTasks() != null ? saved.getDefinedTasks().getTaskName(): null,
                saved.getVehicleType(),
                saved.getLicencePlate(),
                saved.getServiceType(),
                saved.getAdditionalServices(),
                saved.getCustomerName(),
                saved.getEmail(),
                saved.getContactNumber(),
                saved.getTotalServicePrice(),
                saved.getRole(),
                login,
                saved.getAssignee(),
                saved.getAssigneeName()
        );
    }

    @Override
    public AppointmentDto editAppointment(long id, AppointmentDto appointmentDto){
       AppointmentEntity appointment = appointmentRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Appointment not Found"));
       LocalDate oldDate = appointment.getDate();
       LocalTime oldTime = appointment.getTime();

       LocalDate newDate = appointmentDto.getDate();
       LocalTime newTime = appointmentDto.getTime();

       if (!oldDate.equals(newDate) || !oldTime.equals(newTime)) {
           int newBay = IntStream.rangeClosed(1, MAX_BAYS)
                   .filter(bay -> {
                       Optional<AppointmentEntity> existing =
                               appointmentRepository.findByDateAndTimeAndBay(newDate, newTime, bay);

                       return existing.isEmpty();
                   })
                   .findFirst().orElseThrow(()-> new RuntimeException("All Bays are full for selected slot"));
           appointment.setBay(newBay);
       }
       appointment.setDate(newDate);
       appointment.setTime(newTime);
        appointment.setVehicleType(appointmentDto.getVehicleType());
        appointment.setLicencePlate(appointmentDto.getLicencePlate());
        appointment.setServiceType(appointmentDto.getServiceType());
        appointment.setAdditionalServices(appointmentDto.getAdditionalServices());
        appointment.setCustomerName(appointmentDto.getCustomerName());
        appointment.setEmail(appointmentDto.getEmail());
        appointment.setContactNumber(appointmentDto.getContactNumber());
        appointment.setTotalServicePrice(appointmentDto.getTotalServicePrice());

        if (appointmentDto.getTaskId() != null) {
            DefinedTasksEntity task = definedTasksRepository
                    .findById(appointmentDto.getTaskId())
                    .orElseThrow(() -> new RuntimeException("Task not found"));

            appointment.setDefinedTasks(task);
        }


       AppointmentEntity saved = appointmentRepository.save(appointment);
       return appointmentMapper.toAppointmentDto(saved);
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

    @Override
    public AppointmentDto deleteAppointment(long id) {
//        System.out.println("******In DataBase**********");
        try {
            Optional<AppointmentEntity> optionalAppointmentEntity = appointmentRepository.findById(id);
            if (!optionalAppointmentEntity.isPresent()){
                throw new AppException("Form Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            appointmentRepository.deleteById(id);
            return appointmentMapper.toAppointmentDto(optionalAppointmentEntity.get());
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AppointmentDto> getAppointmentsByCusId(String uniqueCusNo) {
        System.out.println("******Customer Unique No**********");
        List<AppointmentEntity> appointments = appointmentRepository.findByUser_UniqueCusNo(uniqueCusNo);
        if (appointments.isEmpty()) {
            throw new AppException("No appointments found for customer ID: " + uniqueCusNo, HttpStatus.NOT_FOUND);
        }
        return appointmentMapper.toAppointmentDtoList(appointments);
    }

    // get Appointment numbers related to the date and time(taskAssign)
    @Override
    public List<AppointmentDto> getAppointmentsByDateAndTime(LocalDate date, LocalTime time, String currentNo){
        List<AppointmentEntity> list = appointmentRepository.findByDateAndTime(date, time, currentNo);
//        if (list.isEmpty()) {
//            throw new AppException("No appointments found " ,HttpStatus.NOT_FOUND);
//        }
        return appointmentMapper.toAppointmentDtoList(list);
    }

    //get Appointments details when select the appointment no(taskAssign)
    @Override
    public AppointmentDto getAppointmentsByAppointmentNo(String appointmentUniqueNo) {
        AppointmentEntity details = appointmentRepository.findByAppointment_UniqueNo(appointmentUniqueNo)
                .orElseThrow(() -> new AppException("Appointment not found", HttpStatus.NOT_FOUND));
        return appointmentMapper.toAppointmentDto(details);
    }

    public String generateAppointmentNumber(AppointmentEntity appointmentEntity) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        String idPart = String.valueOf(appointmentEntity.getId());
        String idPart = String.format("%06d", appointmentEntity.getId()); // 000 - 999

        return "APP" + datePart + idPart;
    }

    public byte[] getBillPdf(Long id) {

        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(
                                "Appointment not found",
                                HttpStatus.NOT_FOUND
                        ));

        byte[] pdf = appointment.getBillPdf();

        if(pdf == null){
            throw new AppException(
                    "Bill not generated yet",
                    HttpStatus.NOT_FOUND
            );
        }

        return pdf;
    }

//    @Override
//    public List<AppointmentDto> getAppointmentsByCusId(Long id) {
//        Optional<AppointmentEntity> optional = appointmentRepository.findById(id);
//        if (!optional.isPresent()) {
//            throw new AppException("Employee not found with ID: " + id, HttpStatus.NOT_FOUND);
//        }
//
//        AppointmentEntity appointmentEntity = optional.get();
//        return appointmentMapper.toAppointmentDto(appointmentEntity);
//    }
}
