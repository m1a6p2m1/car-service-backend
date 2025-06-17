package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OnlineItemDto {
    private long id;
    private String itemCode;
    private String name;
    private String brand;
    private String price;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] image;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageType;

    public OnlineItemDto() {
    }

    public OnlineItemDto(long id, String itemCode, String name, String brand, String price, String description, byte[] image, String imageName, String imageType) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
