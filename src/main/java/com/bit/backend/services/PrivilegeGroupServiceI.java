package com.bit.backend.services;

import com.bit.backend.dtos.PrivilegeGroupDto;

import java.util.List;

public interface PrivilegeGroupServiceI {

    List<PrivilegeGroupDto> getPrivilegeGroups();
    PrivilegeGroupDto addPrivilegeGroup(PrivilegeGroupDto privilegeGroupDto);
    PrivilegeGroupDto updatePrivilegeGroup(long id, PrivilegeGroupDto privilegeGroupDto);
    PrivilegeGroupDto deletePrivilegeGroup(long id, PrivilegeGroupDto privilegeGroupDto);
}
