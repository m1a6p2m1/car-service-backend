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
    @Column(name = "unit_of_measure")
    private String unitOfMeasure;
    @Column(name = "reorder_level")
    private int reorderLevel;
    @Column(name = "description")
    private String description;
//    @Column(name = "image")
//    private byte[] image;
//    @Column(name = "image_name")
//    private String imageName;
//    @Column(name = "image_type")
//    private String imageType;

    public ItemEntity() {
    }

    public ItemEntity(long itemId, String itemCode, String itemName, String itemCategory, String brandName, String unitOfMeasure,int reorderLevel, String description) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.brandName = brandName;
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
