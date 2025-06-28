package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    @Column(name = "timeStamp")
    private Date timeStamp;

    @Column(name = "readStatus")
    private boolean readStatus;

    @Column(name = "targetUser")
    private long targetUser;

    @Column(name = "other")
    private String other;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    public NotificationEntity() {
    }

    public NotificationEntity(Long id, String message, String type, Date timeStamp, boolean readStatus, long targetUser, String other, String email, String mobile) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.timeStamp = timeStamp;
        this.readStatus = readStatus;
        this.targetUser = targetUser;
        this.other = other;
        this.email = email;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public long getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(long targetUser) {
        this.targetUser = targetUser;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
