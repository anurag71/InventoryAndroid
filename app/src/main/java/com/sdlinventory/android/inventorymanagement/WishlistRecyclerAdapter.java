package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WishlistRecyclerAdapter extends RecyclerView.Adapter<WishlistRecyclerAdapter.ViewHolder> {
    private ArrayList<RecyclerViewItemData> android;
    private Context context;

    public WishlistRecyclerAdapter(Context context, ArrayList<RecyclerViewItemData> android) {
        this.android = android;
        this.context = context;
    }


    @Override
    public WishlistRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishlistRecyclerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_product.setText(android.get(i).getProductName());
        viewHolder.img_android.setText(android.get(i).getProduct_image());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_product;
        private TextView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_product = (TextView)view.findViewById(R.id.tv_android1);
            img_android = (TextView) view.findViewById(R.id.tv_android2);
        }
    }

}