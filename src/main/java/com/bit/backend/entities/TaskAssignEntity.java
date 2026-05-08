package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "task_assign")
public class TaskAssignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "appointment_unique_no")
    private String appointmentUniqueNo;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "appointment_time")
    private LocalTime time;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_created_by")
    private String taskCreatedBy;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "licence_plate")
    private String licencePlate;
    @Column(name = "vehicle_type")
    private String vehicleType;
    @Column(name = "email")
    private String email;
    @Column (name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "unique_task_no")
    private String uniqueTaskNo;
    @OneToMany(mappedBy = "taskAssignEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubTaskAssignedEntity> subTasks = new ArrayList<>();
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name="supervisor")
    private Long supervisor;

    public TaskAssignEntity() {
    }

    public TaskAssignEntity(Long id, String appointmentUniqueNo, LocalDate date, LocalTime time, String taskName, String taskCreatedBy, String customerName, String licencePlate, String vehicleType, String email, String description, String status, String uniqueTaskNo, List<SubTaskAssignedEntity> subTasks, Long customerId, Long supervisor) {
        this.id = id;
        this.appointmentUniqueNo = appointmentUniqueNo;
        this.date = date;
        this.time = time;
        this.taskName = taskName;
        this.taskCreatedBy = taskCreatedBy;
        this.customerName = customerName;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.email = email;
        this.description = description;
        this.status = status;
        this.uniqueTaskNo = uniqueTaskNo;
        this.subTasks = subTasks;
        this.customerId = customerId;
        this.supervisor = supervisor;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCreatedBy() {
        return taskCreatedBy;
    }

    public void setTaskCreatedBy(String taskCreatedBy) {
        this.taskCreatedBy = taskCreatedBy;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SubTaskAssignedEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskAssignedEntity> subTaskAssignedEntityList) {
        this.subTasks.clear();
        if (subTaskAssignedEntityList != null) {
            subTaskAssignedEntityList.forEach(this::addSubTask);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUniqueTaskNo() {
        return uniqueTaskNo;
    }

    public void setUniqueTaskNo(String uniqueTaskNo) {
        this.uniqueTaskNo = uniqueTaskNo;
    }

    public void addSubTask(SubTaskAssignedEntity subTaskAssignedEntity) {
        subTasks.add(subTaskAssignedEntity);
        subTaskAssignedEntity.setTaskAssignEntity(this);
    }

    public void removeSubTask(SubTaskAssignedEntity subTaskAssignedEntity) {
        subTasks.remove(subTaskAssignedEntity);
        subTaskAssignedEntity.setTaskAssignEntity(null);
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }
}
