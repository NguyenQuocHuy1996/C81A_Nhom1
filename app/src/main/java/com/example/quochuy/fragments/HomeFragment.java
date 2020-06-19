package com.example.quochuy.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quochuy.adapter.ProductViewPagerAdapter;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;
import com.example.quochuy.sneakerstore.R;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProductViewPagerAdapter pagerAdapter;
    private LinearLayout llQuantity;
    private TextView tvQuantity;
    private FragmentActivity myContext;
    private ArrayList<Product> listCartItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mapView(view);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        llQuantity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(com.example.quochuy.sneakerstore.HomeActivity.this, CartDetailActivity.class);
//                startActivity(intent);
//            }
//        });
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        pagerAdapter = new ProductViewPagerAdapter(fragManager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void mapView(View view) {
        viewPager = view.findViewById(R.id.viewPagerProduct);
        tabLayout = view.findViewById(R.id.tabLayout);
        llQuantity = view.findViewById(R.id.llQuantity);
        tvQuantity = view.findViewById(R.id.tvQuantity);
    }

    public void updateQuantityProduct(Product product) {
        listCartItem = new ArrayList<Product>();
        listCartItem.add(product);
        if (listCartItem.size() == 0) {
            llQuantity.setVisibility(View.GONE);
        } else {
            llQuantity.setVisibility(View.VISIBLE);
            tvQuantity.setText(String.valueOf(listCartItem.size()));
        }
    }

    public ArrayList<Product> getListCartItem() {
        return this.listCartItem;
    }
}
