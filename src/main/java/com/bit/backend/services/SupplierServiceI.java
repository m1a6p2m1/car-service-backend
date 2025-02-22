package com.bit.backend.services;

import com.bit.backend.dtos.SupplierDto;

import java.util.List;

public interface SupplierServiceI {
    SupplierDto addSupplierEntity(SupplierDto supplierDto);
    List<SupplierDto> getData();
    SupplierDto updateForm(long supplierId, SupplierDto supplierDto);
    SupplierDto deleteData(long supplierId);
}
