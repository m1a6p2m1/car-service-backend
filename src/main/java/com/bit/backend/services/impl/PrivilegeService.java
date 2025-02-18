package com.bit.backend.services.impl;

import com.bit.backend.dtos.SystemPrivilegeDto;
import com.bit.backend.dtos.SystemPrivilegeListDto;
import com.bit.backend.entities.Privilege;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.PrivilegeRepository;
import com.bit.backend.services.PrivilegeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService implements PrivilegeServiceI {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto) {

        List<SystemPrivilegeDto> availableSystemPrivilegeDtoList = systemPrivilegeListDto.getSourcePrivileges();
        List<SystemPrivilegeDto> assignedSystemPrivilegeDtoList = systemPrivilegeListDto.getTargetPrivileges();
        List<Privilege> availablePrivilegeList = new ArrayList<>();
        List<Privilege> assignedPrivilegeList = new ArrayList<>();

        for(SystemPrivilegeDto systemPrivilegeDto: availableSystemPrivilegeDtoList) {
            Optional<Privilege> oPrivilege = privilegeRepository.findByAuthId(systemPrivilegeDto.getId());

            if (!oPrivilege.isPresent()) {
                continue;
            }
            oPrivilege.get().setAssigned(0);
            availablePrivilegeList.add(oPrivilege.get());
        }

        for(SystemPrivilegeDto systemPrivilegeDto: assignedSystemPrivilegeDtoList) {
            Optional<Privilege> oPrivilege = privilegeRepository.findByAuthId(systemPrivilegeDto.getId());

            if (!oPrivilege.isPresent()) {
                continue;
            }
            oPrivilege.get().setAssigned(1);
            assignedPrivilegeList.add(oPrivilege.get());
        }

        privilegeRepository.saveAll(availablePrivilegeList);
        privilegeRepository.saveAll(assignedPrivilegeList);

        return null;
    }
}
