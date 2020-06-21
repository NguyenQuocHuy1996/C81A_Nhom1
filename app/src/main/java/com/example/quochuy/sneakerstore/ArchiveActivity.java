package com.example.quochuy.sneakerstore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quochuy.adapter.ProductAdapter;
import com.example.quochuy.common.DummyData;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class ArchiveActivity extends AppCompatActivity {
    private GridView gvProduct;
    private ArrayList<Product> arrayProduct;
    private ProductAdapter adapter;

    static LinearLayout llQuantity;
    static TextView tvQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        /// Map view
        mapView();
        String typeProduct = getIntent().getStringExtra("category");

        /// Custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(typeProduct);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        llQuantity = findViewById(R.id.llQuantity);
        tvQuantity = findViewById(R.id.tvQuantity);

        /// Init data
        DummyData dummyData = new DummyData();
        arrayProduct = dummyData.dummyDataOtherBrand();

        // Khởi tạo dữ liệu mẫu
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

        /// Khởi tạo adapter
        adapter = new ProductAdapter(getApplicationContext(), R.layout.product, arrayProduct, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
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
                MainActivity.updateQuantityProduct(arrayProduct.get(position), getApplicationContext());
            }
        });
        gvProduct.setAdapter(adapter);
    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void mapView() {
        gvProduct = findViewById(R.id.gvProduct);
    }
}