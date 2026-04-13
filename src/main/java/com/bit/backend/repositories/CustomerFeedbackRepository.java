package com.bit.backend.repositories;

import com.bit.backend.entities.AppointmentEntity;
import com.bit.backend.entities.CustomerFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedbackEntity, Long> {

    List<CustomerFeedbackEntity> findByUser_UniqueCusNo(String uniqueCusNo);
}
