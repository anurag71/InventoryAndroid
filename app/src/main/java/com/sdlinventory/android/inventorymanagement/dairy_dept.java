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

public class dairy_dept extends Fragment {

    View view,nav_view;
    RecyclerView recyclerView;
    CoordinatorLayout layout;
    Context context;

    private List<String> ProductName;
    private List<String> ProductPrice;
    private List<String> Brand;

    private final String ProductImage[] = {
            "https://storage.googleapis.com/zopnow-static/images/products/320/amul-butter-v-500-g.png",
            "https://5.imimg.com/data5/YG/TT/MY-19206036/amul-cheese-block-500x500.jpg",
            "https://pickmystore.com/sd/default/160601_2597.JPG",
            "https://d1z88p83zuviay.cloudfront.net/ProductVariantThumbnailImages/18a3ba1f-392a-4841-a17c-186b1a2ac05e_425x425.jpg",
            "https://4.imimg.com/data4/GY/DY/MY-1874306/buttermilk-500x500.jpg",
            "https://5.imimg.com/data5/XF/BE/MY-14448477/amul-taaza-milk-500x500.jpg",
            "https://www.goredforwomen.org/wp-content/uploads/2013/12/Whole-Grains-Fiber-Heart-Health.jpg",
            "http://eboxmart.com/media/catalog/product/cache/1/image/430x430/9df78eab33525d08d6e5fb8d27136e95/a/m/amul_chees_10_slice_200_gm__470_1_2.jpg",
            "https://awesomedairy.com/wp-content/uploads/2016/12/awesome-dairy-epigamia-greek-yogurt-natural-90-gm-image-1-768x768.png",
            "https://5.imimg.com/data5/EH/PB/MY-14097997/fresh-amul-cream-500x500.jpg"



    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_dairy, container, false);

        context = this.getActivity();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());

        ProductName = databaseAccess.getProducts("Inventories",1);
        ProductPrice = databaseAccess.getProductPrice("Inventories",1);
        Brand = databaseAccess.getBrand("Inventories",1);

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
