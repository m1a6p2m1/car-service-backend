package com.bit.backend.services.impl;

import com.bit.backend.dtos.GRNaddedDTO;
import com.bit.backend.dtos.StockDTO;
import com.bit.backend.entities.StockEntitiy;
import com.bit.backend.mappers.StockMapper;
import com.bit.backend.repositories.GRNaddedRepository;
import com.bit.backend.repositories.StockRepository;
import com.bit.backend.services.StockServiceI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//class of the interface
@Service
public class StockService implements StockServiceI {

    //create repository object and mapper object
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final GRNaddedRepository grnaddedRepository;
//    private final JobRoleRepository jobRoleRepository;

    //why put them into the constructor ask mendi?
    public StockService(StockRepository stockRepository, StockMapper stockMapper, GRNaddedRepository grnaddedRepository) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.grnaddedRepository = grnaddedRepository;
    }


    @Override
    public List<StockDTO>  updateStock(List<GRNaddedDTO> grnList) {
        List<StockDTO> savedItems = new ArrayList<>();

        for (GRNaddedDTO dto : grnList) {
           Optional<StockEntitiy> optionalStockEntity = stockRepository.findByItemIDo(dto.getItemID());

            if (optionalStockEntity.isPresent()) {
                System.out.println(optionalStockEntity + "found");
                StockEntitiy newEntity = optionalStockEntity.get();

                int updatedQty = newEntity.getQty() + dto.getQty();
                newEntity.setQty(updatedQty);
               StockEntitiy savedItem = stockRepository.save(newEntity);
                StockDTO stockDTO = stockMapper.toStockDto(savedItem);
                savedItems.add(stockDTO);
            }
        }

        return savedItems;
    }


    @Override
    public StockDTO  updateStockEdit(GRNaddedDTO grNaddedDTO) {

        Optional<StockEntitiy> optionalStockEntity = stockRepository.findByItemIDo(grNaddedDTO.getItemID());
        Integer totalQty = grnaddedRepository.findSumOfQtyByItemId(grNaddedDTO.getItemID());
        if (totalQty == null) {
            totalQty = 0;
        }
        System.out.println(totalQty + " total quantity");
         StockEntitiy newEntity = null;
        if (optionalStockEntity.isPresent()) {
//            System.out.println(optionalStockEntity.get().getId() + " " + optionalStockEntity.get().getStockItemName() + " found");
            newEntity = optionalStockEntity.get();
            int updatedQty = totalQty;
            newEntity.setQty(updatedQty);
            stockRepository.save(newEntity);
        }
        StockEntitiy savedItem = stockRepository.save(newEntity);
        StockDTO stockDTO = stockMapper.toStockDto(savedItem);
        return stockDTO;
    }


    //override the method of interface
    //return type and parameters are FormDemoDto type
    @Override
    public StockDTO addStock(StockDTO stockDTO) {
        System.out.println("*************In Backend***************");

        //need to convert dto to entity type to save from repository
        StockEntitiy stockEntitiy = stockMapper.toStockEntity(stockDTO);

        //save from repository
        StockEntitiy savedItem = stockRepository.save(stockEntitiy);

        //return type is dto and again convert from entity to dto
        StockDTO savedDto = stockMapper.toStockDto(savedItem);
        return savedDto;
    }

    @Override
    public List<StockDTO> getData() {
        List<StockEntitiy> stockEntitiyList = stockRepository.findAll();
        List<StockDTO> stockDTOList = stockMapper.toStockDtoList(stockEntitiyList);
        return stockDTOList;
    }

    @Override
    public StockDTO getQty(long itemID) {
        StockEntitiy stockEntitiy = stockRepository.findByItemID(itemID);
        StockDTO stockDTO = stockMapper.toStockDto(stockEntitiy);
        return stockDTO;
    }


}
