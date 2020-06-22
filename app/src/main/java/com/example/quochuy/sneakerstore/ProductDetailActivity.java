package com.example.quochuy.sneakerstore;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private Button btnAddToCart;
    private Button btnGoToPayment;
    private Product product;
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView sale_price;
    private ImageView imgOnSale;
    private TextView brand;
    private TextView size;
    private TextView color;
    private TextView des;
    private static Context mContext;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        mContext = this;

        /// Mapview
        mapView();
        /// Custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        ///
        product = getIntent().getParcelableExtra("product");
        MapViewAndIntentData();

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.updateQuantityProduct(product,getApplicationContext());
            }
        });

        btnGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.goToCart(mContext);
            }
        });
    }

    private void mapView() {
        image = findViewById(R.id.imgviewImage);
        name = findViewById(R.id.txtTitle);
        price = findViewById(R.id.txtPrice);
        sale_price = findViewById(R.id.txtSalePrice);
        imgOnSale = findViewById(R.id.imgviewOnSale);
        brand = findViewById(R.id.txtBrand);
        size = findViewById(R.id.txtSize);
        color = findViewById(R.id.txtColor);
        des = findViewById(R.id.txtDescription);
        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);
        btnGoToPayment = (Button) findViewById(R.id.btnGoToPayment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                MainActivity.goToCart(mContext);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void MapViewAndIntentData() {
        // Image
        image.setImageResource(product.getImage());
        // Title
        name.setText(product.getTitle());

        // Price
        // Sale Price
        // Img On Sale

        Helper helper = new Helper();

        if (product.getSale_price() > 0 ) {
            price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
            sale_price.setText(String.valueOf(helper.formatCurrency(product.getSale_price())));
            imgOnSale.setImageResource(R.drawable.sale);
        } else {
            sale_price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
        }
        // Brand
        brand.setText(product.getBrand());
        // Size
        size.setText(product.getSize());
        // Color
        color.setText(product.getColor());
        // Description
        des.setText(product.getDescription());
    }
}
