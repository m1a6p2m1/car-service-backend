package com.bit.backend.repositories;

import com.bit.backend.entities.CustomerFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedbackEntity, Long> {
}
