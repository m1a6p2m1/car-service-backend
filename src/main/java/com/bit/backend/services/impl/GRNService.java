package com.bit.backend.services.impl;

import com.bit.backend.dtos.GRNDTO;
import com.bit.backend.entities.GRNEntitiy;
import com.bit.backend.entities.GRNaddedEntitiy;
import com.bit.backend.entities.StockEntitiy;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.GRNMapper;
import com.bit.backend.repositories.GRNRepository;
import com.bit.backend.repositories.GRNaddedRepository;
import com.bit.backend.repositories.StockRepository;
import com.bit.backend.services.GRNServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class of the interface
@Service
public class GRNService implements GRNServiceI {

    //create repository object and mapper object
    private final GRNRepository grnRepository;
    private final GRNaddedRepository grnaddedRepository;
    private final GRNMapper grnMapper;
    private final StockRepository stockRepository;


    public GRNService(GRNRepository grnRepository, GRNaddedRepository grnaddedRepository, GRNMapper grnMapper, StockRepository stockRepository) {
        this.grnRepository = grnRepository;
        this.grnaddedRepository = grnaddedRepository;
        this.grnMapper = grnMapper;
        this.stockRepository = stockRepository;
    }


    @Override
    public GRNDTO addGRNEntity(GRNDTO grndto) {
        System.out.println("*************In Backend***************");

        //need to convert dto to entity type to save from repository
        GRNEntitiy grnEntitiy = grnMapper.toGRNEntity(grndto);

//        //save from repository
        GRNEntitiy savedItem = grnRepository.save(grnEntitiy);
//
//        //return type is dto and again convert from entity to dto
        GRNDTO savedDto = grnMapper.toGRNDto(savedItem);
        return savedDto;

    }

    @Override
    public List<GRNDTO> getData() {
        List<GRNEntitiy> formDemoEntitiyList = grnRepository.findAll();
        List<GRNDTO> grnDtoList = grnMapper.toGRNDtoList(formDemoEntitiyList);
        return grnDtoList;
    }

    @Override
    public GRNDTO deleteOuterGRN(long id) {

        //check the GRN summary record need to delete exist in DB
        Optional<GRNEntitiy> optionalGRNEntitiy =  grnRepository.findById(id);

        //find inner table (GRNadded) records of that GRNsummary record
        List<GRNaddedEntitiy> grNaddedEntitiyList = grnaddedRepository.findAllByGRNNO(id);

        //delete GRN summary Records
        grnRepository.deleteById(id);

        //delete GRNadded records of the GRNsummary record
        grnaddedRepository.deleteAll(grNaddedEntitiyList);
        

        //need to delete quantity of GRNadded records from stock table
        for (GRNaddedEntitiy grNaddedEntitiy : grNaddedEntitiyList) {
            Optional<StockEntitiy> optionalStockEntity = stockRepository.findByItemIDo(grNaddedEntitiy.getItemID());
            int totalQty = Optional.ofNullable(grnaddedRepository.findSumOfQtyByItemId(grNaddedEntitiy.getItemID())).orElse(0);

            System.out.println(totalQty + " total quantity");

            optionalStockEntity.ifPresent(stockEntity -> {
                System.out.println(stockEntity.getId() + " " + stockEntity.getStockItemName() + " found");
                System.out.println(stockEntity.getStockItemName());
                stockEntity.setQty(totalQty);
                stockRepository.save(stockEntity);
            });
        }

        if (!optionalGRNEntitiy.isPresent()) {
            throw  new AppException("form demo entity not exists", HttpStatus.BAD_REQUEST);
        }

        GRNDTO deleteDTO = grnMapper.toGRNDto(optionalGRNEntitiy.get());
        return deleteDTO;
    }

    @Override
    public GRNDTO updateOuterGRN(long id, GRNDTO grnDTO) {

        Optional<GRNEntitiy> optionalGRNEntitiy =  grnRepository.findById(id);

        if (!optionalGRNEntitiy.isPresent()) {
            throw  new AppException("GRN entity not exists", HttpStatus.BAD_REQUEST);
        }

        //need to convert dto to entity type to save from repository
        GRNEntitiy newGRNEntitiy = grnMapper.toGRNEntity(grnDTO);

        //save from repository
        newGRNEntitiy.setGrnno(id);
        GRNEntitiy GRNEntitiy = grnRepository.save(newGRNEntitiy);

        //return type is dto and again convert from entity to dto
        GRNDTO savedDto = grnMapper.toGRNDto(GRNEntitiy);
        return savedDto;
    }

}
