package com.bit.backend.services;

import com.bit.backend.dtos.GRNaddedDTO;
import com.bit.backend.dtos.StockDTO;

import java.util.List;

public interface StockServiceI {

    StockDTO addStock(StockDTO stockDTO);
    List<StockDTO> getData();
    StockDTO getQty(long itemID);
    List<StockDTO> updateStock(List<GRNaddedDTO> grNaddedDTOS);
    StockDTO updateStockEdit(GRNaddedDTO grNaddedDTOS);


//    List<JobRoleDto> getJobRole();
//    FormDemoDto updateFormDemo(long id, FormDemoDto formDemoDto);

//    FormDemoDto deleteFormDemo(long id);
}
