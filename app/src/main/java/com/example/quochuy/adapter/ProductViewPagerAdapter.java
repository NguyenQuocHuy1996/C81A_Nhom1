package com.example.quochuy.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quochuy.fragments.ProductFragment;
import com.example.quochuy.obj.Product;

public class ProductViewPagerAdapter extends FragmentPagerAdapter {


    public ProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if (i == 0)
        {
            fragment = ProductFragment.newInstance(Product.ADIDAS);
        }
        else if (i == 1)
        {
            fragment = ProductFragment.newInstance(Product.NIKE);
        }
        else if (i == 2)
        {
            fragment = ProductFragment.newInstance(Product.CONVERSE);
        }
        else if (i == 3)
        {
            fragment = ProductFragment.newInstance(Product.BALENCIAGA);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = Product.ADIDAS;
        }
        else if (position == 1)
        {
            title = Product.NIKE;
        }
        else if (position == 2)
        {
            title = Product.CONVERSE;
        }
        else if (position == 3)
        {
            title = Product.BALENCIAGA;
        }
        return title;
    }
}
