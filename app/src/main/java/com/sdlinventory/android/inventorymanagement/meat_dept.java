package com.sdlinventory.android.inventorymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class meat_dept extends Fragment {

    View view,nav_view;
    RecyclerView recyclerView;
    CoordinatorLayout layout;
    Context context;

    private List<String> ProductName;
    private List<String> ProductPrice;
    private List<String> Brand;

    private final String ProductImage[] = {
            "https://www.gustini.fr/media/catalog/product/cache/4/image/394x/9df78eab33525d08d6e5fb8d27136e95/7/7/77207_1.jpg",
            "https://www.seriouseats.com/recipes/images/2012/10/20121005-225215-garlic-feta-chicken-sausage.jpg",
            "https://4.imimg.com/data4/KE/LN/MY-10347843/chicken-seekh-kabab-500x500.jpg",
            "https://4.imimg.com/data4/PK/LW/MY-25368938/raw-mutton-500x500.jpg",
            "https://5.imimg.com/data5/UG/AN/MY-9833440/catla-slices-500x500.png",
            "https://d47ro1qos1hnj.cloudfront.net/media/itemdetails/2016/Sep/basa_chunks_with_products__-_800_1473747802.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/81HOqEzV8oL._SL1387_.jpg",
            "https://scene7.samsclub.com/is/image/samsclub/0066564962058_A?wid=1500&hei=1500&fmt=jpg&qlt=80",
            "https://alffarms.com/wp-content/uploads/2017/03/Pork-ham-1-2-180x180.jpg"


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_meat, container, false);

        context = this.getActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());

        ProductName = databaseAccess.getProducts("Inventories",7);
        ProductPrice = databaseAccess.getProductPrice("Inventories",7);
        Brand = databaseAccess.getBrand("Inventories",7);


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
