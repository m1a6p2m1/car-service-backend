package com.bit.backend.services;

import com.bit.backend.dtos.NotificationDto;
import jakarta.mail.MessagingException;

import java.util.List;

public interface NotificationServiceI {

    NotificationDto addNotification(NotificationDto notificationDto) throws MessagingException;
    List<NotificationDto> getUserNotifications(long id);
    boolean changeNotificationStatus(String id);
}
