package com.bit.backend.services;

import com.bit.backend.dtos.GRNDTO;

import java.util.List;

public interface GRNServiceI {


    GRNDTO addGRNEntity(GRNDTO grndto);
    List<GRNDTO> getData();
    GRNDTO updateOuterGRN(long id, GRNDTO grnDTO);
    GRNDTO deleteOuterGRN(long id);
}
