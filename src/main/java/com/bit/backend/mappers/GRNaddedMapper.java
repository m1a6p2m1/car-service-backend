package com.bit.backend.mappers;

import com.bit.backend.dtos.GRNaddedDTO;
import com.bit.backend.entities.GRNaddedEntitiy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

//convert entity to dto and dto to entity
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface GRNaddedMapper {

     GRNaddedEntitiy toGRNaddedEntity(GRNaddedDTO grnaddeddto);
     GRNaddedDTO toGRNaddedDto(GRNaddedEntitiy grnaddedEntitiy);
     List<GRNaddedDTO>  toGRNaddedDtoList(List<GRNaddedEntitiy> grnaddedEntitiyList);
//    List<JobRoleDto> toJobRoleDtoList(List<JobRoleEntitiy> jobRoleEntitiyList);
}
