package com.example.quochuy.myadapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quochuy.fragments.ProductFragment;

public class ProductViewPagerAdapter extends FragmentPagerAdapter {


    public ProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if (i == 0)
        {
            fragment = ProductFragment.newInstance(Product.SNEAKER);
        }
        else if (i == 1)
        {
            fragment = ProductFragment.newInstance(Product.SHIRT);
        }
        else if (i == 2)
        {
            fragment = ProductFragment.newInstance(Product.PAN);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Giày";
        }
        else if (position == 1)
        {
            title = "Áo";
        }
        else if (position == 2)
        {
            title = "Quần";
        }
        return title;
    }
}
