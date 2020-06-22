package com.example.quochuy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.CartItem;
import com.example.quochuy.sneakerstore.R;
import java.util.List;
import static java.lang.String.valueOf;

public class CartItemPaymentAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CartItem> cartList;

    public CartItemPaymentAdapter(Context context, int layout, List<CartItem> cartList) {
        this.context = context;
        this.layout = layout;
        this.cartList = cartList;
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
            holder.quantity = (TextView) convertView.findViewById(R.id.txtQuantity);

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
        holder.quantity.setText("x"+valueOf(cartItem.getQuantity()));

        return convertView;
    }
}
