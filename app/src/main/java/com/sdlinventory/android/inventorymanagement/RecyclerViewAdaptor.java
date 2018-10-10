package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {
    private ArrayList<RecyclerViewItemData> android;
    private Context context;

    public RecyclerViewAdaptor(Context context,ArrayList<RecyclerViewItemData> android) {
        this.android = android;
        this.context = context;
    }


    @Override
    public RecyclerViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptor.ViewHolder viewHolder, int i) {

        viewHolder.tv_product.setText(android.get(i).getProductName());
        viewHolder.img_android.setImageResource(android.get(i).getProduct_image());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_product;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_product = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
        }
    }

}