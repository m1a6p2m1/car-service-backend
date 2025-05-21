package com.bit.backend.services;

import com.bit.backend.dtos.*;

import java.util.List;

public interface UserServiceI {
    UserDto login(CredentialsDto credentialsDto);
    UserDto register(SignUpDto signUpDto);
    List<Integer> getAuthIds(long userId);
    SystemPrivilegeListDto getSystemPrivileges();
    List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto);

    //get saved user data to the user profile form
    UserDto getUserProfile(String login);
    //user profile update
    UserDto updateUserProfile(Long id, UserDto userDto);
}
