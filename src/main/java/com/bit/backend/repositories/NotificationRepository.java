package com.bit.backend.repositories;

import com.bit.backend.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Query(nativeQuery = true, value = "select * from notification where target_user = :id order by id desc")
    List<NotificationEntity> getUserNotification(String id);
}
