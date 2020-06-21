package com.example.quochuy.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.quochuy.adapter.ProductAdapter;
import com.example.quochuy.common.DummyData;
import com.example.quochuy.obj.Product;
import com.example.quochuy.sneakerstore.MainActivity;
import com.example.quochuy.sneakerstore.ProductDetailActivity;
import com.example.quochuy.sneakerstore.R;
import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private GridView gvProduct;
    private ArrayList<Product> arrayProduct;
    private ProductAdapter adapter;

    public static ProductFragment newInstance(String typeProduct) {
        ProductFragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", typeProduct);
        fragment.setArguments(bundle);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            arrayProduct = new ArrayList<Product>();
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
                Product product = new Product(
                        arrayProduct.get(position).getId(),
                        arrayProduct.get(position).getTitle(),
                        arrayProduct.get(position).getPrice(),
                        arrayProduct.get(position).getSale_price(),
                        arrayProduct.get(position).getImage(),
                        arrayProduct.get(position).getCreated_date(),
                        arrayProduct.get(position).getSize(),
                        arrayProduct.get(position).getColor(),
                        arrayProduct.get(position).getBrand(),
                        arrayProduct.get(position).getDescription()
                );
                intent.putExtra("product", product);

                startActivity(intent);
            }

            @Override
            public void onAddCart(int position) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.updateQuantityProduct(arrayProduct.get(position),getContext());
                }
            }
        });
        gvProduct.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void mapView(View view) {
        gvProduct = (GridView) view.findViewById(R.id.gvProduct);
        gvProduct.setNestedScrollingEnabled(true);
    }
}
