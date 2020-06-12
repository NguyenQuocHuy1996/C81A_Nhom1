package com.example.quochuy.myadapter;

public class Product {
    public static final String ADIDAS = "ADIDAS";
    public static final String NIKE = "NIKE";
    public static final String CONVERSE = "CONVERSE";
    public static final String BALENCIAGA = "BALENCIAGA";

    private String title;
    private int price;
    private int sale_price;
    private int image;
    private String created_date;
    private String size;
    private String color;
    private String brand;
    private String description;

    public Product() {};


    public Product(String title, int price, int sale_price, int image, String created_date, String size, String color, String brand, String description) {
        this.title = title;
        this.price = price;
        this.sale_price = sale_price;
        this.image = image;
        this.created_date = created_date;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
