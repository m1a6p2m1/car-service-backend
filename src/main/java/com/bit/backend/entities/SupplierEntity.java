package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long supplierId;

    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "business_address")
    private String businessAddress;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "nic")
    private String nic;
    @Column(name = "email")
    private String email;
    @Column(name = "product_supplied")
    private String productSupplied;

    public SupplierEntity() {
    }

    public SupplierEntity(long supplierId, String supplierName, String companyName, String businessAddress, String phoneNumber, String nic, String email, String productSupplied) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.businessAddress = businessAddress;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
