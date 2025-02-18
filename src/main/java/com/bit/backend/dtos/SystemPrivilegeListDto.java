package com.bit.backend.dtos;

import java.util.List;

public class SystemPrivilegeListDto {

    private List<SystemPrivilegeDto> sourcePrivileges;
    private List<SystemPrivilegeDto> targetPrivileges;

    public List<SystemPrivilegeDto> getSourcePrivileges() {
        return sourcePrivileges;
    }

    public void setSourcePrivileges(List<SystemPrivilegeDto> sourcePrivileges) {
        this.sourcePrivileges = sourcePrivileges;
    }

    public List<SystemPrivilegeDto> getTargetPrivileges() {
        return targetPrivileges;
    }

    public void setTargetPrivileges(List<SystemPrivilegeDto> targetPrivileges) {
        this.targetPrivileges = targetPrivileges;
    }
}
