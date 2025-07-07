package com.bit.backend.mappers;

import com.bit.backend.dtos.StockDTO;
import com.bit.backend.entities.StockEntitiy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

//convert entity to dto and dto to entity
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StockMapper {

     StockEntitiy toStockEntity(StockDTO stockDTO);
     StockDTO toStockDto(Optional<StockEntitiy> stockEntitiy);
     List<StockDTO>  toStockDtoList(List<StockEntitiy> stockEntitiyList);

     StockDTO toStockDto(StockEntitiy savedItem);
//     List<JobRoleDto> toJobRoleDtoList(List<JobRoleEntitiy> jobRoleEntitiyList);
}
