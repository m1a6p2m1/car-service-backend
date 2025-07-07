package com.bit.backend.services.impl;

import com.bit.backend.dtos.GRNaddedDTO;
import com.bit.backend.entities.GRNaddedEntitiy;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.GRNaddedMapper;
import com.bit.backend.repositories.GRNaddedRepository;
import com.bit.backend.services.GRNaddedServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//class of the interface
@Service
public class GRNaddedService implements GRNaddedServiceI {

    //create repository object and mapper object
    private final GRNaddedRepository grnaddedRepository;
    private final GRNaddedMapper grnaddedMapper;
//    private final JobRoleRepository jobRoleRepository;

    public GRNaddedService(GRNaddedRepository grnaddedRepository, GRNaddedMapper grnaddedMapper) {
        this.grnaddedRepository = grnaddedRepository;
        this.grnaddedMapper = grnaddedMapper;

    }


    @Override
    public GRNaddedDTO addGRNaddedEntity(GRNaddedDTO grnaddeddto) {
        System.out.println("*************In Backend***************");

        //need to convert dto to entity type to save from repository
        GRNaddedEntitiy grnaddedEntitiy = grnaddedMapper.toGRNaddedEntity(grnaddeddto);

//        //save from repository
        GRNaddedEntitiy savedItem = grnaddedRepository.save(grnaddedEntitiy);
//
//        //return type is dto and again convert from entity to dto
        GRNaddedDTO savedDto = grnaddedMapper.toGRNaddedDto(savedItem);
        return savedDto;

    }

    @Override
    public List<GRNaddedDTO> getData(long grnno) {
        List<GRNaddedEntitiy> grnaddedEntitiyList = grnaddedRepository.findAllByGRNNO(grnno);
        List<GRNaddedDTO> grnDtoList = grnaddedMapper.toGRNaddedDtoList(grnaddedEntitiyList);
        return grnDtoList;
    }
//
//    @Override
//    public List<JobRoleDto> getJobRole() {
//        List<JobRoleEntitiy> jobRoleEntitiyList = jobRoleRepository.getJobRole();
//        List<JobRoleDto> JobRoleDtoList = formDemoMapper.toJobRoleDtoList(jobRoleEntitiyList);
//        return JobRoleDtoList;
//    }

    @Override
    public GRNaddedDTO updateInnerGRN(long id, GRNaddedDTO grNaddedDTO) {

       Optional<GRNaddedEntitiy> optionalGRNaddedEntitiy =  grnaddedRepository.findById(id);

       if (!optionalGRNaddedEntitiy.isPresent()) {
           throw  new AppException("form demo entity not exists", HttpStatus.BAD_REQUEST);
       }

        //need to convert dto to entity type to save from repository
        GRNaddedEntitiy newGRNEntitiy = grnaddedMapper.toGRNaddedEntity(grNaddedDTO);

        //save from repository
        newGRNEntitiy.setId(id);
        GRNaddedEntitiy GRNEntitiy = grnaddedRepository.save(newGRNEntitiy);

        //return type is dto and again convert from entity to dto
        GRNaddedDTO savedDto = grnaddedMapper.toGRNaddedDto(GRNEntitiy);
        return savedDto;
    }
//
    @Override
    public  GRNaddedDTO deleteAddedGRN(long id) {

        Optional<GRNaddedEntitiy> opformDemoEntity =  grnaddedRepository.findById(id);

        if (!opformDemoEntity.isPresent()) {
            throw  new AppException("form demo entity not exists", HttpStatus.BAD_REQUEST);
        }

         grnaddedRepository.deleteById(id);

        GRNaddedDTO deleteDTO = grnaddedMapper.toGRNaddedDto(opformDemoEntity.get());

        return deleteDTO;
    }
}
