package com.bit.backend.dtos;

public class TimeSlotDto {
    private String time;
    private long bookedCount;

    public TimeSlotDto() {
    }

    public TimeSlotDto(String time, long bookedCount) {
        this.time = time;
        this.bookedCount = bookedCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(long bookedCount) {
        this.bookedCount = bookedCount;
    }
}
