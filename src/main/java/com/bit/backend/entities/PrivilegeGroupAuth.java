package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_group_authentication")
public class PrivilegeGroupAuth {

    public PrivilegeGroupAuth() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "auth_group_id")
    private int authGroupId;

    @Column(name = "auth_id")
    private int authId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAuthGroupId() {
        return authGroupId;
    }

    public void setAuthGroupId(int authGroupId) {
        this.authGroupId = authGroupId;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }
}
