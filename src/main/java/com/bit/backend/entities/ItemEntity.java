package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_category")
    private String itemCategory;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "description")
    private String description;

    public ItemEntity() {
    }

    public ItemEntity(long itemId, String itemCode, String itemName, String itemCategory, String brandName, String supplierName, String description) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.brandName = brandName;
        this.supplierName = supplierName;
        this.description = description;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
