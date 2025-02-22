package com.bit.backend.dtos;

import java.util.List;

public class SupplierDto {

    private long supplierId;
    private String supplierName;
    private String companyName;
    private String businessAddress;
    private String nic;
    private String phoneNumber;
    private String email;
    private String productSupplied;

    public SupplierDto() {
    }

    public SupplierDto(long supplierId, String supplierName, String companyName, String businessAddress, String nic, String phoneNumber, String email, String productSupplied) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.businessAddress = businessAddress;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.productSupplied = productSupplied;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductSupplied() {
        return productSupplied;
    }

    public void setProductSupplied(String productSupplied) {
        this.productSupplied = productSupplied;
    }
}
