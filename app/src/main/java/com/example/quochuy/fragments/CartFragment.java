package com.example.quochuy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quochuy.obj.Product;
import com.example.quochuy.sneakerstore.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    public ArrayList<Product> listCartItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            listCartItem = getArguments().getParcelableArrayList("LIST_CART");
        }
        showData();
    }

    private void showData() {

    }
}
