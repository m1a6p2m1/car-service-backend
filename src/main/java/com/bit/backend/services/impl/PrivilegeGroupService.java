package com.bit.backend.services.impl;

import com.bit.backend.dtos.PrivilegeGroupDto;
import com.bit.backend.entities.PrivilegeGroup;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PrivilegeGroupMapper;
import com.bit.backend.repositories.PrivilegeGroupRepository;
import com.bit.backend.services.PrivilegeGroupServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeGroupService implements PrivilegeGroupServiceI {

    private final PrivilegeGroupRepository privilegeGroupRepository;
    private final PrivilegeGroupMapper privilegeGroupMapper;

    public PrivilegeGroupService(PrivilegeGroupRepository privilegeGroupRepository,
                                                            PrivilegeGroupMapper privilegeGroupMapper) {
        this.privilegeGroupRepository = privilegeGroupRepository;
        this.privilegeGroupMapper = privilegeGroupMapper;
    }

    @Override
    public List<PrivilegeGroupDto> getPrivilegeGroups() {
        List<PrivilegeGroupDto> privilegeGroups = privilegeGroupMapper.toPrivilegeGroupList(privilegeGroupRepository.getActivePrivilageGroups());
        return privilegeGroups;
    }

    @Override
    public PrivilegeGroupDto addPrivilegeGroup(PrivilegeGroupDto privilegeGroupDto) {
        Optional<List<PrivilegeGroup>> oPrivilegeGroups = privilegeGroupRepository.findByGroupNameAndStatus(privilegeGroupDto.getGroupName().trim());
        List<PrivilegeGroup> privilegeGroups = oPrivilegeGroups.get();

        if (privilegeGroups.size() > 0) {
            throw new AppException("Privilege Group Already Exists", HttpStatus.BAD_REQUEST);
        }
        privilegeGroupDto.setStatus(1);
        PrivilegeGroup privilegeGroup = privilegeGroupMapper.toPrivilegeGroup(privilegeGroupDto);
        PrivilegeGroup savedPrivilegeGroup = privilegeGroupRepository.save(privilegeGroup);
        return privilegeGroupMapper.toPrivilegeGroupDto(savedPrivilegeGroup);
    }

    @Override
    public PrivilegeGroupDto updatePrivilegeGroup(long id, PrivilegeGroupDto privilegeGroupDto) {
        Optional<PrivilegeGroup> oPrivilegeGroup = privilegeGroupRepository.findById(id);

        if (!oPrivilegeGroup.isPresent()) {
            throw new AppException("Privilege Group Not Exists", HttpStatus.BAD_REQUEST);
        }

        Optional<List<PrivilegeGroup>> oPrivilegeGroups = privilegeGroupRepository.findByIdAndName(id,  privilegeGroupDto.getGroupName());
        List<PrivilegeGroup> oldPrivilegeGroups = oPrivilegeGroups.get();

        if (oldPrivilegeGroups.size() > 0) {
            throw new AppException("Privilege Group With Same Name Exists!", HttpStatus.BAD_REQUEST);
        }

        PrivilegeGroup newPrivilegeGroup = privilegeGroupMapper.toPrivilegeGroup(privilegeGroupDto);

        PrivilegeGroup privilegeGroup = oPrivilegeGroup.get();
        privilegeGroup.setGroupName(newPrivilegeGroup.getGroupName());
        privilegeGroup.setGroupDescription(newPrivilegeGroup.getGroupDescription());

        PrivilegeGroup savedPrivilegeGroup = privilegeGroupRepository.save(privilegeGroup);
        return privilegeGroupMapper.toPrivilegeGroupDto(savedPrivilegeGroup);
    }

    @Override
    public PrivilegeGroupDto deletePrivilegeGroup(long id, PrivilegeGroupDto privilegeGroupDto) {
        Optional<PrivilegeGroup> oPrivilegeGroup = privilegeGroupRepository.findById(id);

        if (!oPrivilegeGroup.isPresent()) {
            throw new AppException("Privilege Group Not Exists", HttpStatus.BAD_REQUEST);
        }

        PrivilegeGroup privilegeGroup = oPrivilegeGroup.get();
        privilegeGroup.setStatus(0);
        PrivilegeGroup savedPrivilegeGroup = privilegeGroupRepository.save(privilegeGroup);
        return privilegeGroupMapper.toPrivilegeGroupDto(savedPrivilegeGroup);
    }
}
