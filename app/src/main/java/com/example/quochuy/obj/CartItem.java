package com.example.quochuy.obj;

import android.os.Parcel;

public class CartItem extends Product {
    private int quantity;
    private int total;

    public CartItem(int id, String title, int price, int sale_price, int image, String created_date, String size, String color, String brand, String description, int quantity, int total) {
        super(id, title, price, sale_price, image, created_date, size, color, brand, description);
        this.quantity = quantity;
        this.total = total;
    }

    protected CartItem(Parcel in) {
        super(in);
        quantity = in.readInt();
        total = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeInt(quantity);
        dest.writeInt(total);
    }
}
