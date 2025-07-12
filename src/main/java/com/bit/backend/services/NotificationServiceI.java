package com.bit.backend.services;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.dtos.PasswordResetDto;
import com.bit.backend.dtos.TaskAssignDto;
import com.bit.backend.entities.PasswordResetEntity;
import jakarta.mail.MessagingException;

import java.util.List;

public interface NotificationServiceI {

    NotificationDto addNotification(NotificationDto notificationDto) throws MessagingException;
    List<NotificationDto> getUserNotifications(long id);
    boolean changeNotificationStatus(String id);
    boolean sendPasswordResetLink(PasswordResetDto passwordResetDto, String resetLink);
    void sendTaskTrackerNotification(TaskAssignDto taskAssignDto);
}
