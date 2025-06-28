package com.bit.backend.dtos;

import java.util.Date;

public class NotificationDto {

    private long id;
    private String message;
    private String type;
    private Date timeStamp;
    private boolean readStatus;
    private long targetUser;
    private String other;
    private String email;
    private String mobile;

    public NotificationDto() {
    }

    public NotificationDto(long id, String message, String type, Date timeStamp, boolean readStatus, long targetUser, String other, String email, String mobile) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
