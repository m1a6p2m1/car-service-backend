package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_login")
public class EmployeeLoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee")
    private String employee;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private char password;

    public EmployeeLoginEntity() {
    }

    public EmployeeLoginEntity(Long id, String employee, String firstName, String lastName, String userName, char password) {
        this.id = id;
        this.employee = employee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getPassword() {
        return password;
    }

    public void setPassword(char password) {
        this.password = password;
    }
}
