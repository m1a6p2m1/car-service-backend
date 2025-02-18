package com.bit.backend.mappers;

import com.bit.backend.dtos.PrivilegeGroupDto;
import com.bit.backend.entities.PrivilegeGroup;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PrivilegeGroupMapper {
    PrivilegeGroupDto toPrivilegeGroupDto(PrivilegeGroup privilegeGroup);
    PrivilegeGroup toPrivilegeGroup(PrivilegeGroupDto privilegeGroupDto);
    List<PrivilegeGroupDto> toPrivilegeGroupList(List<PrivilegeGroup> privilegeGroups);
}
