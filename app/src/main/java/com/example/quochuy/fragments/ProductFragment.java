package com.example.quochuy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.quochuy.myadapter.DummyData;
import com.example.quochuy.myadapter.Product;
import com.example.quochuy.myadapter.ProductAdapter;
import com.example.quochuy.sneakerstore.MainActivity;
import com.example.quochuy.sneakerstore.ProductDetailActivity;
import com.example.quochuy.sneakerstore.R;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    GridView gvProduct;
    ArrayList<Product> arrayProduct;
    ProductAdapter adapter;

    public static ProductFragment newInstance(String typeProduct) {
        ProductFragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", typeProduct);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        mapView(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DummyData dummyData = new DummyData();

        // Khởi tạo dữ liệu mẫu
        Bundle bundle = getArguments();
        if (bundle != null) {
            String typeProduct = bundle.getString("type");
            if (typeProduct == null) return;
            arrayProduct = new ArrayList<>();
            switch (typeProduct) {
                case Product.ADIDAS:
                    // data adidas
                    arrayProduct = dummyData.dummyDataAdidas();
                    break;
                case Product.NIKE:
                    // data nike
                    arrayProduct = dummyData.dummyDataNike();
                    break;
                case Product.CONVERSE:
                    // data converse
                    arrayProduct = dummyData.dummyDataConverse();
                    break;
                case Product.BALENCIAGA:
                    // data balenciaga
                    arrayProduct = dummyData.dummyDataBalenciaga();
                    break;
                default:
                    // data other
                    arrayProduct = dummyData.dummyDataOtherBrand();
                    break;
            }
        }

        /// Khởi tạo adapter
        adapter = new ProductAdapter(getContext(), R.layout.product, arrayProduct, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);

                // Image
                intent.putExtra("image", arrayProduct.get(position).getImage());
                // Name
                intent.putExtra("name", arrayProduct.get(position).getTitle());
                // Price
                intent.putExtra("price", arrayProduct.get(position).getPrice());
                // Sale Price
                intent.putExtra("sale_price", arrayProduct.get(position).getSale_price());
                // Brand
                intent.putExtra("brand", arrayProduct.get(position).getBrand());
                // Size
                intent.putExtra("size", arrayProduct.get(position).getSize());
                // Color
                intent.putExtra("color", arrayProduct.get(position).getColor());
                // Description
                intent.putExtra("des", arrayProduct.get(position).getDescription());

                startActivity(intent);
            }

            @Override
            public void onAddCart(int position) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.updateQuantityProduct(arrayProduct.get(position));
                }
            }
        });
        gvProduct.setAdapter(adapter);
    }

    private void mapView(View view) {
        gvProduct = (GridView) view.findViewById(R.id.gvProduct);

    }
}
