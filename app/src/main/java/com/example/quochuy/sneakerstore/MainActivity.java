package com.example.quochuy.sneakerstore;
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

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private LinearLayout btnFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                addFragment(new CartFragment());
//                drawerToggle.setDrawerIndicatorEnabled(false);
            }
        });
        initFragment();
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

//        switch (item.getItemId()) {
//            case R.id.search:
//                Toast.makeText(this, "Search button selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.about:
//                Toast.makeText(this, "About button selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.help:
//                Toast.makeText(this, "Help button selected", Toast.LENGTH_SHORT).show();
//                return true;
//        }

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