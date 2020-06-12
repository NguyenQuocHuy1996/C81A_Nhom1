//package com.example.quochuy.fragments;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.GridView;
//
//import com.example.quochuy.myadapter.ProductCategoryAdapter;
//import com.example.quochuy.myadapter.ProductCategory;
//import com.example.quochuy.sneakerstore.ProductDetailActivity;
//import com.example.quochuy.sneakerstore.R;
//import java.util.ArrayList;
//
//public class ProductCategoryFragment extends Fragment {
//    GridView gvProduct;
//    ArrayList<ProductCategory> arrayProductCategory;
//    ProductCategoryAdapter adapter;
//    private String now;
//
//    public static ProductCategoryFragment newInstance(String typeProduct) {
//        ProductCategoryFragment fragment = new ProductCategoryFragment();
//        return fragment;
//    }
//
//    @Nullable
//    @SuppressLint("NewApi")
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_product, container, false);
//        mapView(view);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        /// Khởi tạo dữ liệu mẫu
//        dummyData();
//
//        /// Khởi tạo adapter
//        adapter = new ProductCategoryAdapter(getContext(), R.layout.product, arrayProductCategory, new ProductCategoryAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//                intent.putExtra("key", arrayProductCategory.get(position).getKey());
//                startActivity(intent);
//            }
//        });
//        gvProduct.setAdapter(adapter);
//    }
//
//    private void mapView(View view) {
//        gvProduct = (GridView) view.findViewById(R.id.gvCategory);
//
//    }
//
//    private void dummyData() {
//        arrayProductCategory = new ArrayList<>();
//        /// Khởi tạo dữ liệu mẫu cho category
//        arrayProductCategory.add(new ProductCategory("adidas","ADIDAS",R.drawable.adidas_logo));
//        arrayProductCategory.add(new ProductCategory("nike","NIKE",R.drawable.nike_logo));
//        arrayProductCategory.add(new ProductCategory("converse","CONVERSE",R.drawable.converse_logo));
//        arrayProductCategory.add(new ProductCategory("balenciaga","BALENCIAGE",R.drawable.balenciaga_logo));
//    }
//}
