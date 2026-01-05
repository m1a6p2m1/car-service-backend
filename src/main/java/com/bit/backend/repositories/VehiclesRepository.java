package com.bit.backend.repositories;

import com.bit.backend.entities.VehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesRepository extends JpaRepository<VehiclesEntity , Long> {
    //Auto load licence_plate and vehicle_type when select customer_name
    VehiclesEntity findByCustomerId(Long customerId);
}
