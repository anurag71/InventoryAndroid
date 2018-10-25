package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class offers extends Fragment {

    View view,nav_view;
    RecyclerView recyclerView;
    CoordinatorLayout layout;
    Context context;

    //private List<String> ProductName;
    private final String ProductImage[] = {
            "https://www.ultimatewb.com/blog/wp-content/uploads/10percentoff.png",
            "http://static.wixstatic.com/media/28a190_729d26cb2a5841a49ab6f91e46abdc06~mv2.png",
            "http://flextron-asia.com/wp-content/uploads/2018/04/2018-30-Off-Badge-800x800.jpg"

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offers, container, false);

        context = this.getActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        //databaseAccess.displayWishlist();
        //ProductName = databaseAccess.list;


        bindingVariables();

        displayProducts();

        return view;
    }



    //Function for binding various XML components
    private void bindingVariables(){

        recyclerView = view.findViewById(R.id.card_recycler_view2);
        layout = view.findViewById(R.id.RecyclerLayout2);
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
                        String code;
                        if(position==0)
                        {
                            code="GRAB10";
                        }
                        else if(position==1)
                        {
                            code="GET20";
                        }
                        else
                        {
                            code="SALE30";
                        }
                        Toast toast = Toast.makeText(context,"CPUPON CODE:"+ code, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        ArrayList<RecyclerViewItemData> RecyclerViewItemData = prepareData();
        OffersRecyclerAdapter adapter = new OffersRecyclerAdapter(this.getContext(),RecyclerViewItemData);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<RecyclerViewItemData> prepareData(){

        ArrayList<RecyclerViewItemData> product_list = new ArrayList<>();
        for(int i=0;i<ProductImage.length;i++){
            RecyclerViewItemData RecyclerViewItemData = new RecyclerViewItemData();
            //RecyclerViewItemData.setProduct_name(ProductName.get(i));
            RecyclerViewItemData.setProduct_image(ProductImage[i]);
            product_list.add(RecyclerViewItemData);
        }
        return product_list;
    }
}
