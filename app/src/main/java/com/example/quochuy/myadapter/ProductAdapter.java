package com.example.quochuy.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        /// Ánh xạ
        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView price = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView sale_price = (TextView) convertView.findViewById(R.id.txtSalePrice);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgviewImage);
        Button btnAdd = (Button) convertView.findViewById(R.id.btnAdd);

        /// Gán giá trị
        Product product = productList.get(position);

        title.setText(product.getTitle());
        price.setText(String.valueOf(product.getPrice()));
        sale_price.setText(String.valueOf(product.getSale_price()));
        img.setImageResource(product.getImage());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAddCart(position);
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
