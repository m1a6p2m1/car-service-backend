package com.bit.backend.dtos;

public class SubTaskAssignDto {
    private Long id;
    private String description;

    public SubTaskAssignDto() {
    }

    public SubTaskAssignDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
