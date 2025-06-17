package com.bit.backend.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "online_item")
public class OnlineItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_name")
    private String name;
    @Column(name = "brand_name")
    private String brand;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_type")
    private String imageType;

    public OnlineItemEntity() {
    }

    public OnlineItemEntity(long id, String itemCode, String name, String brand, String price, String description, byte[] image, String imageName, String imageType) {
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
