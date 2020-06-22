package com.example.quochuy.sneakerstore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quochuy.obj.Bill;
import com.example.quochuy.obj.CartItem;

import java.util.ArrayList;

public class CustomerInfoActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtAddress;
    private Button goToPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        /// mapView
        mapview();

        /// Custom action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin giao hàng");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        goToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText().toString().matches("") || edtPhone.getText().toString().matches("") || edtAddress.getText().toString().matches("") ){
                    Toast.makeText(CustomerInfoActivity.this, "Bạn vui lòng nhập đầy đủ thông tin giao hàng", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<CartItem> list_cart = getIntent().getParcelableArrayListExtra("list_cart");
                    /// Using object
//                    Bill bill = getIntent().getParcelableExtra("bill");
//                    if (bill != null) {
//                        Bill new_bill = new Bill(
//                                bill.getTemp(),
//                                bill.getFeeship(),
//                                bill.getCoupon(),
//                                bill.getTotal(),
//                                bill.getPayment_method(),
//                                edtName.getText().toString(),
//                                edtPhone.getText().toString(),
//                                edtAddress.getText().toString(),
//                                bill.getCreated_date()
//                        );
//                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
//                        intent.putExtra("final_bill", new_bill);
//                        intent.putParcelableArrayListExtra("list_cart", list_cart);
//                        startActivity(intent);
//                    }

                    /// Unuse object
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("temp", getIntent().getStringExtra("temp"));
                    intent.putExtra("feeShip", getIntent().getStringExtra("feeShip"));
                    intent.putExtra("coupon", getIntent().getStringExtra("coupon"));
                    intent.putExtra("total_text", getIntent().getStringExtra("total_text"));
                    intent.putExtra("rbPaymentMethod", getIntent().getStringExtra("rbPaymentMethod"));
                    intent.putExtra("name", edtName.getText().toString());
                    intent.putExtra("phone", edtPhone.getText().toString());
                    intent.putExtra("address", edtAddress.getText().toString());
                    intent.putExtra("created_date", getIntent().getStringExtra("created_date"));
                    intent.putParcelableArrayListExtra("list_cart", list_cart);
                    startActivity(intent);
                }

            }
        });
    }

    private void mapview() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        goToPayment = (Button) findViewById(R.id.btnPayment);
    }


    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}