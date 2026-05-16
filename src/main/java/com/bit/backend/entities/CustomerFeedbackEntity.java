package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_feedback")
public class CustomerFeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "licence_plate")
    private String licencePlate;
    @Column(name = "task_number")
    private String uniqueTaskNo;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "service_date")
    private LocalDate serviceDate;
    @Column(name = "service_quality")
    private String serviceQuality;
    @Column(name = "complaint")
    private String complaint;
    @Column(name = "user_recommendation")
    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CustomerFeedbackEntity() {
    }

    public CustomerFeedbackEntity(long id, String userName, String licencePlate, String uniqueTaskNo, String serviceType, LocalDate serviceDate, String serviceQuality, String complaint, String recommendation, User user) {
        this.id = id;
        this.userName = userName;
        this.licencePlate = licencePlate;
        this.uniqueTaskNo = uniqueTaskNo;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.serviceQuality = serviceQuality;
        this.complaint = complaint;
        this.recommendation = recommendation;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getUniqueTaskNo() {
        return uniqueTaskNo;
    }

    public void setUniqueTaskNo(String uniqueTaskNo) {
        this.uniqueTaskNo = uniqueTaskNo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceQuality() {
        return serviceQuality;
    }

    public void setServiceQuality(String serviceQuality) {
        this.serviceQuality = serviceQuality;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
