package com.bit.backend.repositories;

import com.bit.backend.entities.VehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiclesRepository extends JpaRepository<VehiclesEntity, Long> {
    //Auto load licence_plate and vehicle_type when select customer_name
    List<VehiclesEntity> findByUserId(Long userId);
}
