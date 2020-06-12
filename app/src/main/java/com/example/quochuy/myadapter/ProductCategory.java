package com.example.quochuy.myadapter;

public class ProductCategory {
    public String key;
    public String name;
    public int image;

    public ProductCategory(String key, String name, int image) {
        this.key = key;
        this.name = name;
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
