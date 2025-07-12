package com.bit.backend.dtos;

public class SubTaskStatusChangeDto {

    private Long id;
    private String status;

    public SubTaskStatusChangeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SubTaskStatusChangeDto(Long id, String status) {
        this.id = id;
        this.status = status;
    }
}
