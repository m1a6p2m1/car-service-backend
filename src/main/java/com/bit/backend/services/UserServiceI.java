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

    List<Map<String, Object>> getVehicleRegUsers(); //get customer names list into the vehiclesForm customer name field
}
