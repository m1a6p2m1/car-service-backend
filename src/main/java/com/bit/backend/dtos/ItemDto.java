package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto {

    private long itemId;
    private String itemCode;
    private String itemName;
    private String brandName;
    private String itemCategory;
    private String unitOfMeasure;
    private int reorderLevel;
    private String description;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private byte[] image;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String imageName;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String imageType;

    public ItemDto() {
    }

    public ItemDto(long itemId, String itemCode, String itemName, String brandName, String itemCategory, String unitOfMeasure, int reorderLevel, String description) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brandName = brandName;
        this.itemCategory = itemCategory;
        this.unitOfMeasure = unitOfMeasure;
        this.reorderLevel = reorderLevel;
        this.description = description;
//        this.image = image;
//        this.imageName = imageName;
//        this.imageType = imageType;

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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public String getImageType() {
//        return imageType;
//    }
//
//    public void setImageType(String imageType) {
//        this.imageType = imageType;
//    }
}
