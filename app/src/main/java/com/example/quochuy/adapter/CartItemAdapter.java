package com.example.quochuy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.sneakerstore.CartActivity;
import com.example.quochuy.sneakerstore.MainActivity;
import com.example.quochuy.sneakerstore.R;
import java.util.List;

import static com.example.quochuy.sneakerstore.CartActivity.CatchOnItemListView;
import static java.lang.String.valueOf;

public class CartItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CartItem> cartList;
    private OnItemClickListener listener;

    public CartItemAdapter(Context context, int layout, List<CartItem> cartList, OnItemClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.cartList = cartList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView title, price, quantity;
        ImageView img;
        ImageButton btnMinus, btnPlus;
    }


    @SuppressLint("ViewHolder")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder = new ViewHolder();

            /// Ánh xạ
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.price = (TextView) convertView.findViewById(R.id.txtPrice);
            holder.img = (ImageView) convertView.findViewById(R.id.imgviewImage);
            holder.quantity = (TextView) convertView.findViewById(R.id.txtQuantity);
            holder.btnMinus = (ImageButton) convertView.findViewById(R.id.btnMinus);
            holder.btnPlus = (ImageButton) convertView.findViewById(R.id.btnPlus);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        /// Gán giá trị
        final CartItem cartItem = cartList.get(position);
        final Helper helper = new Helper();

        boolean isSale = false;
        if (cartItem.getSale_price() > 0) {
            isSale = true;
        } else {
            isSale = false;
        }

        holder.title.setText(cartItem.getTitle());
        if (isSale) {
            holder.price.setText(valueOf(helper.formatCurrency(cartItem.getSale_price())));
        } else {
            holder.price.setText(valueOf(helper.formatCurrency(cartItem.getPrice())));
        }
        holder.img.setImageResource(cartItem.getImage());
        holder.quantity.setText(valueOf(cartItem.getQuantity()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        final boolean finalIsSale1 = isSale;
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int quantity = Integer.parseInt(holder.quantity.getText().toString()) - 1;
                    int new_price = 0;
                    if (finalIsSale1) {
                        new_price = cartItem.getSale_price() * quantity;
                    } else {
                        new_price = cartItem.getPrice() * quantity;
                    }
                    if (cartItem.getQuantity() <= 1) {
                        CartActivity.arrayCart.remove(position);
                        CartActivity.adapter.notifyDataSetChanged();
                    } else {
                        cartItem.setQuantity(quantity);
                        holder.quantity.setText(valueOf(quantity));
                        holder.price.setText(valueOf(helper.formatCurrency(new_price)));
                    }
                    CartActivity.calculator();
                }
            }
        });
        final boolean finalIsSale = isSale;
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
//                    listener.onPlus(position);
                    int quantity = Integer.parseInt(holder.quantity.getText().toString()) + 1;
                    int new_price = 0;
                    if (finalIsSale) {
                        new_price = cartItem.getSale_price() * quantity;
                    } else {
                        new_price = cartItem.getPrice() * quantity;
                    }
                    cartItem.setQuantity(quantity);
                    holder.quantity.setText(valueOf(quantity));
                    holder.price.setText(valueOf(helper.formatCurrency(new_price)));
                    CartActivity.calculator();
                }
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
