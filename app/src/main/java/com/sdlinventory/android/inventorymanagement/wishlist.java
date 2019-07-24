package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class wishlist extends Fragment {

    View view,nav_view;
    RecyclerView recyclerView;
    CoordinatorLayout layout;
    Context context;

    private List<String> ProductName;
    private List<String> Quantity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wishlist, container, false);

        context = this.getActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        ProductName = databaseAccess.displayWishlistName();
        Quantity = databaseAccess.displayWishlistqty();

        if(!ProductName.isEmpty())
        {
            TextView textView=view.findViewById(R.id.textview1234);
            textView.setVisibility(View.INVISIBLE);
        }


        bindingVariables();

        displayProducts();

        return view;
    }
    public void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }



    //Function for binding various XML components
    private void bindingVariables(){

        recyclerView = view.findViewById(R.id.card_recycler_view1);
        layout = view.findViewById(R.id.RecyclerLayout1);
    }

    //Function to display all the products present in that department
    private void displayProducts(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast toast = Toast.makeText(context, "Long press to remove item", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override public void onLongItemClick(View view, int position) {

                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
                        databaseAccess.deleteTitle(ProductName.get(position));
                        Toast toast = Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT);
                        toast.show();
                        refresh();

                    }
                })
        );

        ArrayList<RecyclerViewItemData> RecyclerViewItemData = prepareData();
        WishlistRecyclerAdapter adapter = new WishlistRecyclerAdapter(this.getContext(),RecyclerViewItemData);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<RecyclerViewItemData> prepareData(){

        ArrayList<RecyclerViewItemData> product_list = new ArrayList<>();
        for(int i=0;i<ProductName.size();i++){
            RecyclerViewItemData RecyclerViewItemData = new RecyclerViewItemData();
            RecyclerViewItemData.setProduct_name(ProductName.get(i));
            RecyclerViewItemData.setProduct_image(Quantity.get(i));
            product_list.add(RecyclerViewItemData);
        }
        return product_list;
    }
}
