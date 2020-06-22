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
import com.example.quochuy.common.Helper;
import com.example.quochuy.obj.Product;
import com.example.quochuy.sneakerstore.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> productList;
    private OnItemClickListener listener;

    public ProductAdapter(Context context, int layout, List<Product> productList, OnItemClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView title, price, sale_price;
        ImageView img, imgOnSale;
        ImageButton btnAdd;
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
            holder.sale_price = (TextView) convertView.findViewById(R.id.txtSalePrice);
            holder.img = (ImageView) convertView.findViewById(R.id.imgviewImage);
            holder.imgOnSale = (ImageView) convertView.findViewById(R.id.imgviewOnSale);
            holder.btnAdd = (ImageButton) convertView.findViewById(R.id.btnAdd);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        /// Gán giá trị
        Product product = productList.get(position);
        Helper helper = new Helper();

        holder.title.setText(product.getTitle());
        if (product.getSale_price() > 0 ) {
            holder.price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
            holder.sale_price.setText(String.valueOf(helper.formatCurrency(product.getSale_price())));
            holder.imgOnSale.setImageResource(R.drawable.sale);
        } else {
            holder.sale_price.setText(String.valueOf(helper.formatCurrency(product.getPrice())));
        }
        holder.img.setImageResource(product.getImage());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAddCart(position);
//                    holder.btnAdd.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onClick(int position);
        void onAddCart(int position);
    }
}
