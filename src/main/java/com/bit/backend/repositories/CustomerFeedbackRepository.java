package com.bit.backend.repositories;

import com.bit.backend.entities.AppointmentEntity;
import com.bit.backend.entities.CustomerFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedbackEntity, Long> {

    List<CustomerFeedbackEntity> findByUser_UniqueCusNo(String uniqueCusNo);
    //update reviewed feedbacks
    List<CustomerFeedbackEntity> findByStatus(String status);

    //Feedback Report
    @Query("SELECT serviceQuality as quality, count(serviceQuality) as cnt FROM CustomerFeedbackEntity group by serviceQuality")
    List<Map<String, Object>> getCustomerFeedbackRates();
}
