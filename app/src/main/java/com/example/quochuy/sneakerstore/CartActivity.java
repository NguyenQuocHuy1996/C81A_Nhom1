package com.example.quochuy.sneakerstore;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quochuy.adapter.CartItemAdapter;
import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.obj.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CartActivity extends AppCompatActivity {
    private static ListView lvCartItem;
    public static ArrayList<CartItem> arrayCart;
    private static TextView txtCartItem;
    public static CartItemAdapter adapter;
    private ImageButton btnMinus;

    static TextView temp;
    static TextView feeShip;
    static TextView coupon;
    static TextView total_text;

    static CartActivity cartActivity;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        /// Map view
        mapView();

        /// Custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Giỏ hàng");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        /// Init data
        showData();
    }

    private void mapView() {
        lvCartItem = (ListView) findViewById(R.id.lvCartItem);
        btnMinus = (ImageButton) findViewById(R.id.btnMinus);
        txtCartItem = (TextView) findViewById(R.id.txtCartItem);
        temp =  (TextView) findViewById(R.id.txtTemp);
        feeShip =  (TextView) findViewById(R.id.txtFeeShip);
        coupon =  (TextView) findViewById(R.id.txtCoupon);
        total_text =  (TextView) findViewById(R.id.txtTotal);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showData() {
        /// Khởi tạo dữ liệu
        arrayCart = new ArrayList<CartItem>();
        arrayCart = getIntent().getParcelableArrayListExtra("list_cart");

        /// Khởi tạo adapter
        adapter = new CartItemAdapter(getApplicationContext(), R.layout.cart_item, arrayCart, new CartItemAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                Product product = new Product(
                        arrayCart.get(position).getId(),
                        arrayCart.get(position).getTitle(),
                        arrayCart.get(position).getPrice(),
                        arrayCart.get(position).getSale_price(),
                        arrayCart.get(position).getImage(),
                        arrayCart.get(position).getCreated_date(),
                        arrayCart.get(position).getSize(),
                        arrayCart.get(position).getColor(),
                        arrayCart.get(position).getBrand(),
                        arrayCart.get(position).getDescription()
                );
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        lvCartItem.setAdapter(adapter);
        calculator();
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void calculator() {
        long total = 0;
        int fee_ship = 15000;
        int fee_coupon = 15000;
        long final_total = 0;
        if (arrayCart.size() <= 0) {
            lvCartItem.setVisibility(View.GONE);
            txtCartItem.setVisibility(View.VISIBLE);
            total = 0;
            fee_ship = 0;
            fee_coupon = 0;
            final_total = 0;
        } else {
            lvCartItem.setVisibility(View.VISIBLE);
            txtCartItem.setVisibility(View.GONE);
            Helper helper = new Helper();
            total = 0;
            fee_ship = 15000;
            fee_coupon = 15000;
            final_total = 0;
            for(CartItem c : arrayCart){
                if (c.getSale_price() == 0) {
                    total += c.getPrice() * c.getQuantity();
                } else {
                    total += c.getSale_price() * c.getQuantity();
                }
            }

            temp.setText(helper.formatCurrency(total));
            feeShip.setText(helper.formatCurrency(fee_ship));
            coupon.setText("-"+helper.formatCurrency(fee_coupon));

            final_total = total + fee_ship - fee_coupon ;
            total_text.setText(helper.formatCurrency(final_total));
        }
    }


    public static void CatchOnItemListView() {
        lvCartItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cartActivity);
                builder.setTitle("Xác nhận xóa sản phẩm ?");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (arrayCart.size() > 0) {
                            arrayCart.remove(position);
                            adapter.notifyDataSetChanged();
                            calculator();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                        calculator();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}