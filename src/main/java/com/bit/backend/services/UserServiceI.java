package com.bit.backend.services;

import com.bit.backend.dtos.*;

import java.util.List;
import java.util.Map;

public interface UserServiceI {
    UserDto login(CredentialsDto credentialsDto) throws Exception;
    UserDto register(SignUpDto signUpDto);
    List<Integer> getAuthIds(long userId);
    SystemPrivilegeListDto getSystemPrivileges();
    List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto);

    //get saved user data to the user profile form
    UserDto getUserProfile(String login);
    //user profile update
    UserDto updateUserProfile(Long id, UserDto userDto);
    PasswordResetDto forgotPassword(PasswordResetDto passwordResetDto);
    void resetPassword(String token, String newPassword);

    //get customer names list into the vehiclesForm customer name field
    List<Map<String, Object>> getVehicleRegUsers();

    //get customer details when enter phone number in appointment form
    UserDto getCustomerByPhone(String contactNumber);

    boolean isUsernameTaken(String username);

    boolean isContactNumberIsExists(String contactNumber);

    EmployeeCredentialDto getEmployeeLogin(Long employeeId);

    EmployeeCredentialDto updateEmployeeLogin(Long employeeId, EmployeeCredentialDto dto);

    CustomerCredentialDto getCustomerLogin(Long customerId);
    CustomerCredentialDto updateCustomerLogin(Long customerId, CustomerCredentialDto dto);
}
