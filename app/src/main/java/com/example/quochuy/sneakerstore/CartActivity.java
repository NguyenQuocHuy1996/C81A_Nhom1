package com.example.quochuy.sneakerstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quochuy.adapter.CartItemAdapter;
import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.Bill;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.obj.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private static ListView lvCartItem;
    public static ArrayList<CartItem> arrayCart;
    private static TextView txtCartItem;
    private static Button btnPayment;
    private RadioGroup rdgPaymentMethod;
    private RadioButton rbPaymentMethod;
    private RadioGroup rdgCoupon;
    private RadioButton rbCoupon;
    private static int coupon_type = 0;
    public static CartItemAdapter adapter;
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

        /// Handler coupon choose
        handlerCoupon();

        /// Go to payment
        goToPayment();
    }

    private void handlerCoupon() {
        rdgCoupon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rdCoupon1:
                        // do operations specific to this selection
                        coupon_type = 20;
                        break;
                    case R.id.rdCoupon2:
                        // do operations specific to this selection
                        coupon_type = 15000;
                        break;
                    case R.id.rdCoupon3:
                        // do operations specific to this selection
                        coupon_type = 50;
                        break;
                    case R.id.rdCoupon4:
                        // do operations specific to this selection
                        coupon_type = 15;
                        break;
                    default:
                        coupon_type = 0;
                        break;
                }
                calculator();
            }
        });
    }

    private void goToPayment() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Get Payment Method text
                int selectedPaymentMethod = rdgPaymentMethod.getCheckedRadioButtonId();
                rbPaymentMethod = (RadioButton) findViewById(selectedPaymentMethod);
                Helper helper = new Helper();
                Intent intent = new Intent(getApplicationContext(), CustomerInfoActivity.class);
//                /// Using object
//                Bill bill = new Bill(
//                        temp.getText().toString(),
//                        feeShip.getText().toString(),
//                        coupon.getText().toString(),
//                        total_text.getText().toString(),
//                        rbPaymentMethod.getText().toString(),
//                        "",
//                        "",
//                        "",
//                        helper.getCurrentDateTime()
//
//                );
//                intent.putExtra("bill", bill);

                /// Unuse Object
                intent.putExtra("temp", temp.getText().toString());
                intent.putExtra("feeShip", feeShip.getText().toString());
                intent.putExtra("coupon", coupon.getText().toString());
                intent.putExtra("total_text", total_text.getText().toString());
                intent.putExtra("rbPaymentMethod", rbPaymentMethod.getText().toString());
                intent.putExtra("created_date", helper.getCurrentDateTime());

                intent.putParcelableArrayListExtra("list_cart", arrayCart);
                startActivity(intent);
            }
        });
    }

    private void mapView() {
        lvCartItem = (ListView) findViewById(R.id.lvCartItem);
        btnPayment = (Button) findViewById(R.id.btnPayment);
        txtCartItem = (TextView) findViewById(R.id.txtCartItem);
        temp =  (TextView) findViewById(R.id.txtTemp);
        feeShip =  (TextView) findViewById(R.id.txtFeeShip);
        coupon =  (TextView) findViewById(R.id.txtCoupon);
        total_text =  (TextView) findViewById(R.id.txtTotal);
        rdgPaymentMethod = (RadioGroup) findViewById(R.id.rdgPaymentInfo);
        rdgCoupon = (RadioGroup) findViewById(R.id.rdgCoupon);
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
        long fee_coupon = 0;
        long final_total = 0;
        if (arrayCart.size() <= 0) {
            lvCartItem.setVisibility(View.GONE);
            btnPayment.setVisibility(View.GONE);
            txtCartItem.setVisibility(View.VISIBLE);
//            total = 0;
//            fee_ship = 0;
//            fee_coupon = 0;
//            final_total = 0;
        } else {
            lvCartItem.setVisibility(View.VISIBLE);
            btnPayment.setVisibility(View.VISIBLE);
            txtCartItem.setVisibility(View.GONE);
            Helper helper = new Helper();
            for(CartItem c : arrayCart){
                if (c.getSale_price() == 0) {
                    total += c.getPrice() * c.getQuantity();
                } else {
                    total += c.getSale_price() * c.getQuantity();
                }
            }
            if (coupon_type != 0){
                if (coupon_type == 15000) {
                    fee_coupon = 15000;
                } else {
                    fee_coupon = (coupon_type * total) / 100;
                }
            }

            temp.setText(helper.formatCurrency(total));
            feeShip.setText(helper.formatCurrency(fee_ship));
            coupon.setText("-"+helper.formatCurrency(fee_coupon));

            final_total = total + fee_ship - fee_coupon ;
            total_text.setText(helper.formatCurrency(final_total));
        }
    }


//    public static void CatchOnItemListView() {
//        lvCartItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(cartActivity);
//                builder.setTitle("Xác nhận xóa sản phẩm ?");
//                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này ?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (arrayCart.size() > 0) {
//                            arrayCart.remove(position);
//                            adapter.notifyDataSetChanged();
//                            calculator();
//                        }
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        adapter.notifyDataSetChanged();
//                        calculator();
//                    }
//                });
//                builder.show();
//                return true;
//            }
//        });
//    }

    // Handler click back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}