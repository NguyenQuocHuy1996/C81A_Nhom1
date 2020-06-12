//package com.example.quochuy.myadapter;
//
//import android.content.Context;
//import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.example.quochuy.sneakerstore.R;
//
//import java.util.List;
//
//public class ProductCategoryAdapter extends BaseAdapter {
//    private Context context;
//    private int layout;
//    private List<ProductCategory> productCategoryList;
//    private OnItemClickListener listener;
//
//    public ProductCategoryAdapter(Context context, int layout, List<ProductCategory> productCategoryList, OnItemClickListener listener) {
//        this.context = context;
//        this.layout = layout;
//        this.productCategoryList = productCategoryList;
//        this.listener = listener;
//    }
//
//    @Override
//    public int getCount() {
//        return productCategoryList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        convertView = inflater.inflate(layout, null);
//
//        /// Ánh xạ
//        TextView title = (TextView) convertView.findViewById(R.id.txtProductCategoryName;
//        ImageView img = (ImageView) convertView.findViewById(R.id.imgProductCategoryImage);
//
//        /// Gán giá trị
//        ProductCategory productCategory = productCategoryList.get(position);
//
//        title.setText(productCategory.getName());
//        img.setImageResource(productCategory.getImage());
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(position);
//                }
//            }
//        });
//
//        return convertView;
//    }
//
//    public interface OnItemClickListener {
//        void onClick(int position);
//    }
//}
