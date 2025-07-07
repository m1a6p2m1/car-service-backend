package com.bit.backend.controllers;

import com.bit.backend.dtos.GRNaddedDTO;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.GRNaddedServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
public class GRNaddedControlloer {

    //FormDemoServiceI type variable
     private final GRNaddedServiceI grnServiceI;

     //Dependency Injection (constructor injection)- Call constructor - assign value to the variable
    public GRNaddedControlloer(GRNaddedServiceI grnServiceI) {
        this.grnServiceI = grnServiceI;
    }

    //first create DTO and Entitiy classes with constructors and getter setter
     //when front end request coming like -  /form-demo in url -  addForm method with ResponseEntity return type is  called
     //@RequestBody annotation = response coming from frontend ( JSON type) convert to formDemoDto type object
    @PostMapping("/inner")
    public ResponseEntity<GRNaddedDTO> addForm(@RequestBody GRNaddedDTO grnaddeddto){

        GRNaddedDTO grnaddeddtoresponse = grnServiceI.addGRNaddedEntity(grnaddeddto);
         return ResponseEntity.created(URI.create("/grn"+grnaddeddtoresponse.getId())).body(grnaddeddtoresponse);
    }

    @GetMapping("/get-inner/{grnno}")
    public ResponseEntity<List<GRNaddedDTO>> getGRNs(@PathVariable long grnno){
         try{
             List<GRNaddedDTO> grnDtoList = grnServiceI.getData(grnno);
             return ResponseEntity.ok().body(grnDtoList);
         } catch (Exception e) {
             throw new AppException(" Get mapping Failed " + e, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @PutMapping("/innergrn/{id}")
    public ResponseEntity<GRNaddedDTO> updateInnerGRN(@PathVariable long id, @RequestBody GRNaddedDTO grNaddedDTO){
          GRNaddedDTO responsegrnaddeddto = grnServiceI.updateInnerGRN(id,grNaddedDTO);
          return ResponseEntity.ok(responsegrnaddeddto);
    }
//
//
    @DeleteMapping("/innergrn/{id}")
    public ResponseEntity<GRNaddedDTO> deleteAddedGRN(@PathVariable long id){
        GRNaddedDTO grNaddedDTO = grnServiceI.deleteAddedGRN(id);
        return ResponseEntity.ok(grNaddedDTO);
    }

}
