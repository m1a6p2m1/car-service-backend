package com.bit.backend.repositories;

import com.bit.backend.entities.VehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface VehiclesRepository extends JpaRepository<VehiclesEntity, Long> {
    //Auto load licence_plate and vehicle_type when select customer_name
    List<VehiclesEntity> findByUserId(Long customerId);

    List<VehiclesEntity> findByUser_UniqueCusNo(String uniqueCusNo);

    @Query(nativeQuery = true, value = "SELECT v.id as id, u.id as customerId, u.first_name as customerName, v.licence_plate as licencePlate, v.vehicle_model as vehicleModel, v.vehicle_type as vehicleType, u.unique_cus_no as uniqueCusNo FROM vehicles v JOIN app_user u ON v.customer_id = u.id;")
    List<Map<String, Object>> getVehiclesList();

//    @Query("SELECT v.licencePlate FROM VehiclesEntity v JOIN User u ON v.id = u.id")
//    List<VehiclesEntity> findByCustomer_Id(Long customerId);
}
