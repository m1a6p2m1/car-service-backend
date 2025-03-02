package com.bit.backend.dtos;

public class ItemDto {

    private long itemId;
    private String itemCode;
    private String itemName;
    private String brandName;
    private String itemCategory;
    private String supplierName;
    private String description;
    private byte[] itemImage;

    public ItemDto() {
    }

    public ItemDto(long itemId, String itemCode, String itemName, String brandName, String itemCategory, String supplierName, String description, byte[] itemImage) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brandName = brandName;
        this.itemCategory = itemCategory;
        this.supplierName = supplierName;
        this.description = description;
        this.itemImage = itemImage;

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }
}
