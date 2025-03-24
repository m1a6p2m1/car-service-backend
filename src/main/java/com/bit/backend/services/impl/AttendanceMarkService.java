package com.bit.backend.services.impl;

import com.bit.backend.dtos.AttendanceMarkDto;
import com.bit.backend.dtos.AttendanceMarkEmployeeDto;
import com.bit.backend.entities.AttendanceMarkEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AttendanceMarkMapper;
import com.bit.backend.repositories.AttendanceMarkRepository;
import com.bit.backend.services.AttendanceMarkServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceMarkService implements AttendanceMarkServiceI {
    private final AttendanceMarkRepository attendanceMarkRepository;
    private final AttendanceMarkMapper attendanceMarkMapper;

    public AttendanceMarkService(AttendanceMarkRepository attendanceMarkRepository, AttendanceMarkMapper attendanceMarkMapper) {
        this.attendanceMarkRepository = attendanceMarkRepository;
        this.attendanceMarkMapper = attendanceMarkMapper;
    }

    @Override
    public AttendanceMarkDto addAttendanceMarkEntity(AttendanceMarkDto attendanceMarkDto) {
//        System.out.println("=================In the Back end==================");
        try {
            AttendanceMarkEntity attendanceMarkEntity = attendanceMarkMapper.toAttendanceMarkEntity(attendanceMarkDto);
            AttendanceMarkEntity savedItem = attendanceMarkRepository.save(attendanceMarkEntity);
            AttendanceMarkDto saveDto = attendanceMarkMapper.toAttendanceMarkDto(savedItem);
            return saveDto;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AttendanceMarkEmployeeDto> getData() {
//        System.out.println("=================In the Back end==================");
        try {
            List<AttendanceMarkEmployeeDto> attendanceMarkDtoList = attendanceMarkRepository.getEmployeeAttendanceDetails();
//            List<AttendanceMarkDto> attendanceMarkDtoList = attendanceMarkMapper.toAttendanceDtoList(attendanceMarkEntityList);
            return attendanceMarkDtoList;
        }catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
