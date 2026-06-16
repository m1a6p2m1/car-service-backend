package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class VehiclesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "customer_name")
//    private String customerName;
    @Column(name = "licence_plate", unique = true)
    private String licencePlate;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Column(name = "vehicle_model")
    private String vehicleModel;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    public VehiclesEntity() {
    }

    public VehiclesEntity(Long id,  String licencePlate, String vehicleType, String vehicleModel, User user) {
        this.id = id;
//        this.customerName = customerName;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
