package com.bit.backend.dtos;

public class EmployeeLoginDto {
    private long id;
    private String employee;
    private String firstName;
    private String lastName;
    private String userName;
    private char password;

    public EmployeeLoginDto() {
    }

    public EmployeeLoginDto(long id, String employee, String firstName, String lastName, String userName, char password) {
        this.id = id;
        this.employee = employee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
