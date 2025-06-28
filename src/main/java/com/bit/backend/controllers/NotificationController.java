package com.bit.backend.controllers;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.NotificationServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class NotificationController {

    private NotificationServiceI notificationServiceI;

    public NotificationController(NotificationServiceI notificationServiceI) {
        this.notificationServiceI = notificationServiceI;
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationDto> addNotification(@RequestBody NotificationDto notificationDto) {
        try {
            NotificationDto notificationDtoResponse = notificationServiceI.addNotification(notificationDto);
            return ResponseEntity.created(URI.create("/notification" + notificationDtoResponse.getType())).body(notificationDtoResponse);
        } catch (Exception e) {
            throw new AppException("Fail to send notification" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@PathVariable long id) {
        List<NotificationDto> notificationDtoList = notificationServiceI.getUserNotifications(id);
        return ResponseEntity.ok(notificationDtoList);
    }

    @PutMapping("/notification/changeStatus/{id}")
    public ResponseEntity<Boolean> changeNotificationStatus(@PathVariable String id) {
        boolean status = notificationServiceI.changeNotificationStatus(id);
        return ResponseEntity.ok(status);
    }
}
