package com.example.quochuy.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.quochuy.sneakerstore.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> productList;

    public ProductAdapter(Context context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        /// Ánh xạ
        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView price = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView sale_price = (TextView) convertView.findViewById(R.id.txtSalePrice);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgviewImage);

        /// Gán giá trị
        Product product = productList.get(position);

        title.setText(product.getTitle());
        price.setText(String.valueOf(product.getPrice()));
        sale_price.setText(String.valueOf(product.getSale_price()));
        img.setImageResource(product.getImage());

        return convertView;
    }
}
