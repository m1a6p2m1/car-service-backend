package com.bit.backend.dtos;

public record SignUpDto(Long id, String firstName, String lastName,String nic, String email , String contactNumber , String address , String login, char[] password, String role, Long employeeId, Long customerId) { // add role
}
