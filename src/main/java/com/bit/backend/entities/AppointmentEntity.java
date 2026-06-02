package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_unique_no")
    private String appointmentUniqueNo;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "appointment_time")
    private LocalTime time;

    /** 1‑3 for the three washing bays */
    @Column(name = "bay")
    private Integer bay;
    @Column(name = "status")
    private String status;
//    @Column(name = "task_name")
//    private String taskName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private DefinedTasksEntity definedTasks;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Column(name = "licence_plate")
    private String licencePlate;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "additional_services")
    private String additionalServices;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "email")
    private String email;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "total_service_price")
    private Double totalServicePrice;
    @Column(name = "role")
    private String role;
    @Column(name="assignee")
    private Long assignee;
    @Column(name = "assignee_name")
    private String assigneeName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public AppointmentEntity() {}

    public AppointmentEntity(Long id, String appointmentUniqueNo, LocalDate date, LocalTime time, Integer bay, String status, DefinedTasksEntity definedTasks, String vehicleType, String licencePlate, String serviceType, String additionalServices, String customerName, String email, String contactNumber, Double totalServicePrice, String role, Long assignee, String assigneeName, User user) {
        this.id = id;
        this.appointmentUniqueNo = appointmentUniqueNo;
        this.date = date;
        this.time = time;
        this.bay = bay;
        this.status = status;
        this.definedTasks = definedTasks;
        this.vehicleType = vehicleType;
        this.licencePlate = licencePlate;
        this.serviceType = serviceType;
        this.additionalServices = additionalServices;
        this.customerName = customerName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.totalServicePrice = totalServicePrice;
        this.role = role;
        this.assignee = assignee;
        this.assigneeName = assigneeName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppointmentUniqueNo() {
        return appointmentUniqueNo;
    }

    public void setAppointmentUniqueNo(String appointmentUniqueNo) {
        this.appointmentUniqueNo = appointmentUniqueNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DefinedTasksEntity getDefinedTasks() {
        return definedTasks;
    }

    public void setDefinedTasks(DefinedTasksEntity definedTasks) {
        this.definedTasks = definedTasks;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {return contactNumber; }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Double getTotalServicePrice() {
        return totalServicePrice;
    }

    public void setTotalServicePrice(Double totalServicePrice) {
        this.totalServicePrice = totalServicePrice;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
