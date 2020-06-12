package com.example.quochuy.sneakerstore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quochuy.myadapter.DummyData;

import static java.lang.Integer.parseInt;

public class ProductDetailActivity extends AppCompatActivity {
//    Button btnBack;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ///
        MapViewAndIntentData();

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void MapViewAndIntentData() {
        // Image
        ImageView image = findViewById(R.id.imgviewImage);
        image.setImageResource(getIntent().getIntExtra("image",0));
        // Title
        TextView name = findViewById(R.id.txtTitle);
        name.setText(getIntent().getStringExtra("name"));

        // Price
        TextView price = findViewById(R.id.txtPrice);
        // Sale Price
        TextView sale_price = findViewById(R.id.txtSalePrice);
        // Img On Sale
        ImageView imgOnSale = findViewById(R.id.imgviewOnSale);

        DummyData dummy = new DummyData();

        if (getIntent().getIntExtra("sale_price",0) > 0 ) {
            price.setText(String.valueOf(dummy.formatCurrency(getIntent().getIntExtra("price", 0))));
            sale_price.setText(String.valueOf(dummy.formatCurrency(getIntent().getIntExtra("sale_price", 0))));
            imgOnSale.setImageResource(R.drawable.sale);
        } else {
            sale_price.setText(String.valueOf(dummy.formatCurrency(getIntent().getIntExtra("price", 0))));
        }
        // Brand
        TextView brand = findViewById(R.id.txtBrand);
        brand.setText(getIntent().getStringExtra("brand"));
        // Size
        TextView size = findViewById(R.id.txtSize);
        size.setText(getIntent().getStringExtra("size"));
        // Color
        TextView color = findViewById(R.id.txtColor);
        color.setText(getIntent().getStringExtra("color"));
        // Description
        TextView des = findViewById(R.id.txtDescription);
        des.setText(getIntent().getStringExtra("des"));

//        btnBack = findViewById(R.id.btnBack);
    }
}
