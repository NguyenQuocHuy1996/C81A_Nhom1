package com.example.quochuy.sneakerstore;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quochuy.adapter.ProductViewPagerAdapter;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private LinearLayout btnGoToCart;
    private LinearLayout btnGoToAbout;
    private LinearLayout btnGoToContact;
    private LinearLayout btnGoToAdidas;
    private LinearLayout btnGoToNike;
    private LinearLayout btnGoToConverse;
    private LinearLayout btnGoToBalenciaga;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProductViewPagerAdapter pagerAdapter;
    static LinearLayout llQuantity;
    static TextView tvQuantity;
    DrawerLayout drawerLayout;
    static ArrayList<CartItem> listCartItem = new ArrayList<CartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        registerReceiver(receiver, intentFilter);

        /// Map view
        mapView();

        /// Custome action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        getSupportActionBar().setTitle("Sneaker Store");

        /// Handler Left menu
        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        /// Handler event click left menu item
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCart();
            }
        });
        btnGoToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        btnGoToContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        btnGoToAdidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                intent.putExtra("category",Product.ADIDAS);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        btnGoToNike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                intent.putExtra("category",Product.NIKE);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        btnGoToConverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                intent.putExtra("category",Product.CONVERSE);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        btnGoToBalenciaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                intent.putExtra("category",Product.BALENCIAGA);
                MainActivity.this.startActivity(intent);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
        pagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        /// Click cart
        llQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.cart:
                goToCart();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public static void updateQuantityProduct(Product product, Context context) {
        boolean isFound = false;
        for(CartItem p : listCartItem){
            if (p.getId() == product.getId()) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng !", Toast.LENGTH_SHORT).show();
        } else {
            int total = 0;
            if (product.getSale_price() == 0) {
                total = product.getPrice();
            } else {
                total = product.getSale_price();
            }
            CartItem cartItem = new CartItem(product.getId(),product.getTitle(),product.getPrice(),product.getSale_price(),product.getImage(),product.getCreated_date(),product.getSize(),product.getColor(),product.getBrand(),product.getDescription(),1, total);
            listCartItem.add(cartItem);
        }
        if (listCartItem.size() == 0) {
            llQuantity.setVisibility(View.GONE);
        } else {
            llQuantity.setVisibility(View.VISIBLE);
            tvQuantity.setText(String.valueOf(listCartItem.size()));
        }
    }

    private void goToCart() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        intent.putParcelableArrayListExtra("list_cart", listCartItem);
        MainActivity.this.startActivity(intent);
    }

    private void mapView() {
        viewPager = findViewById(R.id.viewPagerProduct);
        tabLayout = findViewById(R.id.tabLayout);
        llQuantity = findViewById(R.id.llQuantity);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnGoToCart = (LinearLayout) findViewById(R.id.btnGoToCart);
        btnGoToAbout = (LinearLayout) findViewById(R.id.btnGoToAbout);
        btnGoToContact = (LinearLayout) findViewById(R.id.btnGoToContact);
        btnGoToAdidas = (LinearLayout) findViewById(R.id.btnGoToAdidas);
        btnGoToNike = (LinearLayout) findViewById(R.id.btnGoToNike);
        btnGoToConverse = (LinearLayout) findViewById(R.id.btnGoToConverse);
        btnGoToBalenciaga = (LinearLayout) findViewById(R.id.btnGoToBalenciaga);
    }
}