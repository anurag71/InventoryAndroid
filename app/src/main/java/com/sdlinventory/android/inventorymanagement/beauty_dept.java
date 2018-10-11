package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class beauty_dept extends Fragment {

    View view,nav_view;
    RecyclerView recyclerView;
    CoordinatorLayout layout;
    Context context;

    private List<String> ProductName;
    private List<String> ProductPrice;
    private List<String> Brand;

    private final String ProductImage[] = {
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN",
            "https://goo.gl/images/BnxMhN"


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_beauty, container, false);

        context = this.getActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());

        ProductName = databaseAccess.getProducts("Inventories",2);
        ProductPrice = databaseAccess.getProductPrice("Inventories",2);
        Brand = databaseAccess.getBrand("Inventories",2);


        bindingVariables();

        displayProducts();

        return view;
    }



    //Function for binding various XML components
    private void bindingVariables(){

        recyclerView = view.findViewById(R.id.card_recycler_view);
        layout = view.findViewById(R.id.RecyclerLayout);
    }

    //Function to display all the products present in that department
    private void displayProducts(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent product = new Intent(getActivity(),ProductView.class);
                        product.putExtra("ProductImage",ProductImage[position]);
                        product.putExtra("ProductName",ProductName.get(position));
                        product.putExtra("ProductPrice",ProductPrice.get(position));
                        product.putExtra("ProductBrand",Brand.get(position));
                        startActivity(product);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<RecyclerViewItemData> RecyclerViewItemData = prepareData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getContext(),RecyclerViewItemData);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<RecyclerViewItemData> prepareData(){

        ArrayList<RecyclerViewItemData> product_list = new ArrayList<>();
        for(int i=0;i<ProductName.size();i++){
            RecyclerViewItemData RecyclerViewItemData = new RecyclerViewItemData();
            RecyclerViewItemData.setProduct_name(ProductName.get(i));
            RecyclerViewItemData.setProduct_image(ProductImage[i]);
            product_list.add(RecyclerViewItemData);
        }
        return product_list;
    }
}
