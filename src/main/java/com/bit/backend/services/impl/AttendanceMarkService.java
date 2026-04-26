package com.bit.backend.services.impl;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AttendanceMarkMapper;
import com.bit.backend.repositories.AttendanceMarkRepository;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.services.AttendanceMarkServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceMarkService implements AttendanceMarkServiceI {
    private final AttendanceMarkRepository attendanceMarkRepository;
    private final AttendanceMarkMapper attendanceMarkMapper;
    private final EmployeeRepository employeeRepository;

    public AttendanceMarkService(AttendanceMarkRepository attendanceMarkRepository, AttendanceMarkMapper attendanceMarkMapper, EmployeeRepository employeeRepository) {
        this.attendanceMarkRepository = attendanceMarkRepository;
        this.attendanceMarkMapper = attendanceMarkMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<AttendanceMarkDto> saveAttendance(List<AttendanceMarkDto> attendanceList) {
//        System.out.println("=================In the Back end==================");
        try {
            List<AttendanceMarkEntity> entities = new ArrayList<>();

            for (AttendanceMarkDto dto: attendanceList) {
                EmployeeEntity employeeEntity = employeeRepository.findByEmpNumber(dto.getEmployeeId()).orElseThrow(() ->
                        new RuntimeException("Employee not found:" + dto.getEmployeeId()));

                Optional<AttendanceMarkEntity> existing = attendanceMarkRepository.findByEmployee_EmpNumberAndDate(
                        dto.getEmployeeId(),
                        dto.getDate()
                        );
                AttendanceMarkEntity entity;

                if (existing.isPresent()) {
                    //Update
                    entity = existing.get();
                    entity.setAttendanceStatus(dto.getAttendanceStatus());
                } else {
                    //Insert
                    entity = attendanceMarkMapper.toAttendanceMarkEntity(dto, employeeEntity);
                }
                entities.add(entity);

//                boolean exists = attendanceMarkRepository.existsByEmployee_EmpNumberAndDate(
//                        dto.getEmployeeId(),dto.getDate()
//                );
//
//                if (exists) { continue; } // skip duplicates
//
//                AttendanceMarkEntity attendanceMarkEntity = attendanceMarkMapper.toAttendanceMarkEntity(dto, employeeEntity);
//                entities.add(attendanceMarkEntity);

            }
            List<AttendanceMarkEntity> savedEntities = attendanceMarkRepository.saveAll(entities);
            return attendanceMarkMapper.toAttendanceDtoList(savedEntities);

        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AttendanceMarkDto> getAttendanceByDate(LocalDate date) {
        try{
            List<AttendanceMarkEntity> entities = attendanceMarkRepository.findByDate(date);
            return attendanceMarkMapper.toAttendanceDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Error fetching attendance: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @Override
//    public List<AttendanceMarkEmployeeDto> getData() {
////        System.out.println("=================In the Back end==================");
//        try {
//            List<AttendanceMarkEmployeeDto> attendanceMarkDtoList = attendanceMarkRepository.getEmployeeAttendanceDetails();
////            List<AttendanceMarkDto> attendanceMarkDtoList = attendanceMarkMapper.toAttendanceDtoList(attendanceMarkEntityList);
//            return attendanceMarkDtoList;
//        }catch (Exception e){
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @Override
//    public List<EmployeeEntity> getActiveEmployees() {
//        try {
//            List<EmployeeEntity> activeEmployeeList = employeeRepository.getActiveEmployeesList();
//            return activeEmployeeList;
//        }catch (Exception e){
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public List<Map<String, Object>> getActiveEmployees() {
        return employeeRepository.getActiveEmployeesList();
    }
}
