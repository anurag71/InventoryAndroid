package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OffersRecyclerAdapter extends RecyclerView.Adapter<OffersRecyclerAdapter.ViewHolder> {
    private ArrayList<RecyclerViewItemData> android;
    private Context context;

    public OffersRecyclerAdapter(Context context, ArrayList<RecyclerViewItemData> android) {
        this.android = android;
        this.context = context;
    }


    @Override
    public OffersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offers_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OffersRecyclerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_product.setText(android.get(i).getProductName());
        Picasso.with(context).load(android.get(i).getProduct_image()).into(viewHolder.img_android);
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

            tv_product = (TextView)view.findViewById(R.id.tv_android123);
            img_android = (ImageView) view.findViewById(R.id.img_android123);
        }
    }

}