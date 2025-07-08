package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_groups")
public class PrivilegeGroup {

    public PrivilegeGroup() {}

    public PrivilegeGroup(Long id, String groupName, String groupDescription, int status, boolean isDefault) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.status = status;
        this.isDefault = isDefault;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private String groupDescription;

    @Column(name = "status")
    private int status;


    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
