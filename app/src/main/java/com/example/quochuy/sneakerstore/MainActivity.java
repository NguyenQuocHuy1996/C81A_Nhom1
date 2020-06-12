package com.example.quochuy.sneakerstore;
import com.example.quochuy.myadapter.ProductViewPagerAdapter;
import com.example.quochuy.myadapter.Product;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ProductViewPagerAdapter pagerAdapter;
    LinearLayout llQuantity;
    TextView tvQuantity;

    ArrayList<Product> listSelectedProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Custom Action Bar
        customActionBar();

        /// Ánh xạ
        mapView();

        llQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartDetailActivity.class);
                startActivity(intent);
            }
        });

        pagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        listSelectedProduct = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void mapView() {
        viewPager = findViewById(R.id.viewPagerProduct);
        tabLayout = findViewById(R.id.tabLayout);
        llQuantity = findViewById(R.id.llQuantity);
        tvQuantity = findViewById(R.id.tvQuantity);
    }

    private void customActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setTitle("ABC");
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