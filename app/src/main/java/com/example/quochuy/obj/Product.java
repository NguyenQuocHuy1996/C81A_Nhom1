package com.example.quochuy.obj;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Product implements Parcelable {
    public static final String ADIDAS = "ADIDAS";
    public static final String NIKE = "NIKE";
    public static final String CONVERSE = "CONVERSE";
    public static final String BALENCIAGA = "BALENCIAGA";

    private int id;
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


    public Product(int id, String title, int price, int sale_price, int image, String created_date, String size, String color, String brand, String description) {
        this.id = id;
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

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        price = in.readInt();
        sale_price = in.readInt();
        image = in.readInt();
        created_date = in.readString();
        size = in.readString();
        color = in.readString();
        brand = in.readString();
        description = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(price);
        dest.writeInt(sale_price);
        dest.writeInt(image);
        dest.writeString(created_date);
        dest.writeString(size);
        dest.writeString(color);
        dest.writeString(brand);
        dest.writeString(description);
    }
}
