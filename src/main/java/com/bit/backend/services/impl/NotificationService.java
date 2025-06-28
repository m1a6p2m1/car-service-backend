package com.bit.backend.services.impl;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.entities.NotificationEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.NotificationMapper;
import com.bit.backend.repositories.NotificationRepository;
import com.bit.backend.services.NotificationServiceI;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements NotificationServiceI {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final JavaMailSender javaMailSender;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, JavaMailSender javaMailSender) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) throws MessagingException {
        NotificationEntity notificationEntity = notificationMapper.toNotificationEntity(notificationDto);
        notificationEntity.setTimeStamp(new Date());
        NotificationEntity savedNotification = notificationRepository.save(notificationEntity);
        NotificationDto savedDto = notificationMapper.toNotificationDto(savedNotification);

        String htmlBody = """
    <div style="font-family: Arial, sans-serif;">
        <h2 style="color: #4CAF50;">📢 Carservice Notification </h2>
        <p>Hello Student,</p>
        <p>Your task has been <strong>successfully started</strong>.</p>
        <p>Thank you for using the car service portal.</p>
        <hr>
        <small>This is an automated message. Do not reply.</small>
    </div>
""";

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("chamith109mendes@gmail.com"); // CAR SERVICE EMAIL
//        simpleMailMessage.setTo(notificationDto.getEmail());
//        simpleMailMessage.setSubject(notificationDto.getMessage());
//        simpleMailMessage.setText("Your assignment has been uploaded successfully!");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom("carservice@gmail.com"); // add the correct mail
        helper.setTo(notificationDto.getEmail());
        helper.setSubject("Demo Email"); // Add correct subject
        helper.setText(htmlBody, true); // Set true for HTML

        if (!notificationDto.getEmail().isEmpty()) {
            javaMailSender.send(mimeMessage);
        }

        return savedDto;
    }

    @Override
    public List<NotificationDto> getUserNotifications(long id) {
        String idString = Long.toString(id);
        List<NotificationEntity> notificationEntityList = notificationRepository.getUserNotification(idString);
        List<NotificationDto> notificationDtoList = notificationMapper.toNotificationDtoList(notificationEntityList);
        return notificationDtoList;
    }

    @Override
    public boolean changeNotificationStatus(String id) {
        Long notificationId = Long.parseLong(id);

        try {
            Optional<NotificationEntity> optionalNotificationEntity = notificationRepository.findById(notificationId);

            if (!optionalNotificationEntity.isPresent()) {
                throw new AppException("Can't update notification status", HttpStatus.BAD_REQUEST);
            }

            NotificationEntity oldNotificationEntity = optionalNotificationEntity.get();
            oldNotificationEntity.setReadStatus(true);
            NotificationEntity updatedNotificationEntity = notificationRepository.save(oldNotificationEntity);
            if (updatedNotificationEntity.isReadStatus() == true) {
                return true;
            } else return false;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
