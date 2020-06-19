package com.example.quochuy.sneakerstore;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.Product;

import java.io.Serializable;

public class ProductDetailActivity extends AppCompatActivity {
//    Button btnBack;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ///
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        ///
        MapViewAndIntentData();

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void MapViewAndIntentData() {
        Product product = (Product) getIntent().getSerializableExtra("product");
        // Image
        ImageView image = findViewById(R.id.imgviewImage);
        image.setImageResource(product.getImage());
        // Title
        TextView name = findViewById(R.id.txtTitle);
        name.setText(product.getTitle());

        // Price
        TextView price = findViewById(R.id.txtPrice);
        // Sale Price
        TextView sale_price = findViewById(R.id.txtSalePrice);
        // Img On Sale
        ImageView imgOnSale = findViewById(R.id.imgviewOnSale);

        Helper helper = new Helper();

        if (product.getSale_price() > 0 ) {
            price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
            sale_price.setText(String.valueOf(helper.formatCurrency(product.getSale_price())));
            imgOnSale.setImageResource(R.drawable.sale);
        } else {
            sale_price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
        }
        // Brand
        TextView brand = findViewById(R.id.txtBrand);
        brand.setText(product.getBrand());
        // Size
        TextView size = findViewById(R.id.txtSize);
        size.setText(product.getSize());
        // Color
        TextView color = findViewById(R.id.txtColor);
        color.setText(product.getColor());
        // Description
        TextView des = findViewById(R.id.txtDescription);
        des.setText(product.getDescription());

//        btnBack = findViewById(R.id.btnBack);
    }
}
