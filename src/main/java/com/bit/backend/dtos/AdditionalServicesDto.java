package com.bit.backend.dtos;

public class AdditionalServicesDto {
    private long id;
    private String additionalServicesName;
    private Double additionalServicePrice;
    private String status;

    public AdditionalServicesDto() {}

    public AdditionalServicesDto(long id, String additionalServicesName, Double additionalServicePrice, String status) {
        this.id = id;
        this.additionalServicesName = additionalServicesName;
        this.additionalServicePrice = additionalServicePrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
