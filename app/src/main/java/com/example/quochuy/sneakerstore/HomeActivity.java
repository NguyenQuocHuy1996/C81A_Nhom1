package com.example.quochuy.sneakerstore;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quochuy.adapter.ProductViewPagerAdapter;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class HomeActivity extends MainActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProductViewPagerAdapter pagerAdapter;
    private LinearLayout llQuantity;
    private TextView tvQuantity;

    ArrayList<Product> listSelectedProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /// Ánh xạ
        mapView();

        llQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartDetailActivity.class);
                startActivity(intent);
            }
        });

        pagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        listSelectedProduct = new ArrayList<>();
    }

    private void mapView() {
        viewPager = findViewById(R.id.viewPagerProduct);
        tabLayout = findViewById(R.id.tabLayout);
        llQuantity = findViewById(R.id.llQuantity);
        tvQuantity = findViewById(R.id.tvQuantity);
    }

    public void updateQuantityProduct(Product product) {
        listSelectedProduct.add(product);
        if (listSelectedProduct.size() == 0) {
            llQuantity.setVisibility(View.GONE);
        } else {
            llQuantity.setVisibility(View.VISIBLE);
            tvQuantity.setText(String.valueOf(listSelectedProduct.size()));
        }
    }

    public ArrayList<Product> getListSelectedProduct() {
        return this.listSelectedProduct;
    }
}