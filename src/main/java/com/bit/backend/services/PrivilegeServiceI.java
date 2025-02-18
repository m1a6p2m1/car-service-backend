package com.bit.backend.services;

import com.bit.backend.dtos.SystemPrivilegeListDto;

import java.util.List;

public interface PrivilegeServiceI {
    List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto);
}
