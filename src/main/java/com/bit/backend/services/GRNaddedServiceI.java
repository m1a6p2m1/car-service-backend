package com.bit.backend.services;

import com.bit.backend.dtos.GRNaddedDTO;

import java.util.List;

public interface GRNaddedServiceI {

    GRNaddedDTO addGRNaddedEntity(GRNaddedDTO grnaddeddto);
    List<GRNaddedDTO> getData(long grnno);
    GRNaddedDTO updateInnerGRN(long id, GRNaddedDTO grNaddedDTO);
    GRNaddedDTO deleteAddedGRN(long id);
}
