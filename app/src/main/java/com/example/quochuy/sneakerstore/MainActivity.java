package com.example.quochuy.sneakerstore;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.quochuy.fragments.CartFragment;
import com.example.quochuy.fragments.HomeFragment;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private LinearLayout btnFrag;
    private ArrayList<Product> listCartItem;

    private IntentFilter intentFilter = new IntentFilter("OPEN_CART");
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction() != null && intent.getAction().equals("OPEN_CART")) {
//                addFragment(new CartFragment());
//            }
//        }
//    };

    public void showCart(ArrayList<Product> listCartItem) {
        if (listCartItem != null && !listCartItem.isEmpty()) {
            this.listCartItem = listCartItem;
        }
        CartFragment fragment = new CartFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("LIST_CART", listCartItem);
        fragment.setArguments(bundle);

        addFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        registerReceiver(receiver, intentFilter);

        ///
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        getSupportActionBar().setTitle("Home");

        ///
        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        ///
        btnFrag = (LinearLayout) findViewById(R.id.btnGoToCart);
        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart(MainActivity.this.listCartItem);
//                drawerToggle.setDrawerIndicatorEnabled(false);
            }
        });
        initFragment();
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
                showCart(this.listCartItem);
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

    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container_body, homeFragment);
        ft.commit();
    }
    protected void replaceFragmentContent(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fmgr = getSupportFragmentManager();
            FragmentTransaction ft = fmgr.beginTransaction();
            ft.replace(R.id.container_body, fragment);
            ft.commit();
        }
    }
    protected void addFragment(Fragment fragment) {
        FragmentManager fmgr = getSupportFragmentManager();
        FragmentTransaction ft = fmgr.beginTransaction();
        ft.add(R.id.container_body, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }
}