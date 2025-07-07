package com.bit.backend.mappers;

import com.bit.backend.dtos.GRNDTO;
import com.bit.backend.entities.GRNEntitiy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

//convert entity to dto and dto to entity
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface GRNMapper {

     GRNEntitiy toGRNEntity(GRNDTO grndto);
     GRNDTO toGRNDto(GRNEntitiy grnEntitiy);
     List<GRNDTO>  toGRNDtoList(List<GRNEntitiy> grnEntitiyList);
//    List<JobRoleDto> toJobRoleDtoList(List<JobRoleEntitiy> jobRoleEntitiyList);
}
