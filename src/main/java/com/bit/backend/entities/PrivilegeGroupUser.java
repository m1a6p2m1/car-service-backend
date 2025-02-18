package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_group_users")
public class PrivilegeGroupUser {

    public PrivilegeGroupUser() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "auth_group_id")
    private int authGroupId;

    @Column(name = "user_id")
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
