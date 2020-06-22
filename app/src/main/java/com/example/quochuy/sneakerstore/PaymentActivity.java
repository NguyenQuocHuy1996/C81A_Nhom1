package com.example.quochuy.sneakerstore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quochuy.adapter.CartItemAdapter;
import com.example.quochuy.adapter.CartItemPaymentAdapter;
import com.example.quochuy.obj.Bill;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private TextView txtTemp;
    private TextView txtFeeShip;
    private TextView txtCoupon;
    private TextView txtTotal;
    private ListView lvCartItem;
    private TextView txtOrderCode;
    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAddress;
    private TextView txtPaymentMethod;
    private Button btnConfirm;
    private TextView txtCreatedOrder;
    private CartItemPaymentAdapter adapter;
    private ArrayList<CartItem> list_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        /// Map View
        mapView();

        /// Custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        getSupportActionBar().setTitle("Thông tin đơn hàng");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        /// Show data
        showData();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showData(){
        showCartItem();
//        /// Using object
//        Bill bill = getIntent().getParcelableExtra("final_bill");
//        if (bill != null) {
//            txtTemp.setText(bill.getTemp());
//            txtFeeShip.setText(bill.getFeeship());
//            txtCoupon.setText(bill.getCoupon());
//            txtTotal.setText(bill.getTotal());
//            txtName.setText(bill.getcustomer_name());
//            txtPhone.setText(bill.getcustomer_phone());
//            txtAddress.setText(bill.getcustomer_address());
//            txtPaymentMethod.setText(bill.getPayment_method());
//            txtOrderCode.setText("SNEAKER08");
//        }

        /// Un use object
        txtTemp.setText(getIntent().getStringExtra("temp"));
        txtFeeShip.setText(getIntent().getStringExtra("feeShip"));
        txtCoupon.setText(getIntent().getStringExtra("coupon"));
        txtTotal.setText( getIntent().getStringExtra("total_text"));
        txtPaymentMethod.setText(getIntent().getStringExtra("rbPaymentMethod"));
        txtOrderCode.setText("SNEAKER08");
        txtName.setText(getIntent().getStringExtra("name"));
        txtPhone.setText(getIntent().getStringExtra("phone"));
        txtAddress.setText(getIntent().getStringExtra("address"));
        txtCreatedOrder.setText(getIntent().getStringExtra("created_date"));


    }

    private void showCartItem() {
        /// Khởi tạo dữ liệu
        list_cart = new ArrayList<CartItem>();
        list_cart = getIntent().getParcelableArrayListExtra("list_cart");
        if (list_cart != null) {
            // Show cart
            /// Khởi tạo adapter
            adapter = new CartItemPaymentAdapter(getApplicationContext(), R.layout.cart_item_payment, list_cart);
            lvCartItem.setAdapter(adapter);
        }
    }

    private void mapView() {
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtFeeShip = (TextView) findViewById(R.id.txtFeeShip);
        txtCoupon = (TextView) findViewById(R.id.txtCoupon);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtOrderCode = (TextView) findViewById(R.id.txtOrderCode);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtPaymentMethod = (TextView) findViewById(R.id.txtPaymentMethod);
        txtCreatedOrder = (TextView) findViewById(R.id.txtCreatedOrder);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        lvCartItem = (ListView) findViewById(R.id.lvCartItem);

    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}