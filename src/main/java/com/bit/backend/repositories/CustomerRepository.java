package com.bit.backend.repositories;

import com.bit.backend.entities.CustomerEntity;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("SELECT cusId as id, firstName as firstName, lastName as lastName FROM CustomerEntity")
    List<Map<String, Object>> getTaskCustomerList();

    boolean existsByContactNumber(String contactNumber);
    boolean existsByNic(String nic);
}
