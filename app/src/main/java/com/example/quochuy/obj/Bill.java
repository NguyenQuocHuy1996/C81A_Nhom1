package com.example.quochuy.obj;

import android.os.Parcel;
import android.os.Parcelable;


public class Bill implements Parcelable{
    private String temp;
    private String feeship;
    private String coupon;
    private String total;
    private String payment_method;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String created_date;

    public Bill(String temp, String feeship, String coupon, String total, String payment_method, String customerName, String customerPhone, String customerAddress, String created_date) {
        this.temp = temp;
        this.feeship = feeship;
        this.coupon = coupon;
        this.total = total;
        this.payment_method = payment_method;
        this.customer_name = customerName;
        this.customer_phone = customerPhone;
        this.customer_address = customerAddress;
        this.created_date = created_date;
    }

    protected Bill(Parcel in) {
        temp = in.readString();
        coupon = in.readString();
        total = in.readString();
        payment_method = in.readString();
        customer_name = in.readString();
        customer_phone = in.readString();
        customer_address = in.readString();
        created_date = in.readString();
    }

    public static final Creator<Bill> CREATOR = new Creator<Bill>() {
        @Override
        public Bill createFromParcel(Parcel in) {
            return new Bill(in);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeeship() {
        return feeship;
    }

    public void setFeeship(String feeship) {
        this.feeship = feeship;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getcustomer_name() {
        return customer_name;
    }

    public void setcustomer_name(String customerName) {
        customer_name = customerName;
    }

    public String getcustomer_phone() {
        return customer_phone;
    }

    public void setcustomer_phone(String customerPhone) {
        customer_phone = customerPhone;
    }

    public String getcustomer_address() {
        return customer_address;
    }

    public void setcustomer_address(String customerAddress) {
        customer_address = customerAddress;
    }


    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temp);
        dest.writeString(feeship);
        dest.writeString(coupon);
        dest.writeString(total);
        dest.writeString(payment_method);
        dest.writeString(customer_name);
        dest.writeString(customer_phone);
        dest.writeString(customer_address);
        dest.writeString(created_date);
    }
}
