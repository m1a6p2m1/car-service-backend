package com.bit.backend.dtos;

public class EmployeeCredentialDto {
    private Long employeeId;
    private String login;
    private String password;

    public EmployeeCredentialDto() {
    }

    public EmployeeCredentialDto(Long employeeId, String login, String password) {
        this.employeeId = employeeId;
        this.login = login;
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
