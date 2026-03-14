package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "additional_services")
public class AdditionalServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "additional_services_name")
    private String additionalServicesName;
    @Column(name = "additional_services_price")
    private Double additionalServicePrice;
    @Column(name = "status")
    private String status;

    public AdditionalServicesEntity() {
    }

    public AdditionalServicesEntity(Long id, String additionalServicesName, Double additionalServicePrice, String status) {
        this.id = id;
        this.additionalServicesName = additionalServicesName;
        this.additionalServicePrice = additionalServicePrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdditionalServicesName() {
        return additionalServicesName;
    }

    public void setAdditionalServicesName(String additionalServicesName) {
        this.additionalServicesName = additionalServicesName;
    }

    public Double getAdditionalServicePrice() {
        return additionalServicePrice;
    }

    public void setAdditionalServicePrice(Double additionalServicePrice) {
        this.additionalServicePrice = additionalServicePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
