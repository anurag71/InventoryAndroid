package com.sdlinventory.android.inventorymanagement;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProductView extends AppCompatActivity {

    ImageView prodImage;
    TextView prodName, prodPrice, Brand;
    Button addWish;
    int quantity;
    String product;
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d = new Dialog(ProductView.this);
        setContentView(R.layout.activity_product_view);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bindingVariables();

        //Displaying product details
        Picasso.with(ProductView.this).load(getIntent().getStringExtra("ProductImage")).resize(240, 120).into(prodImage);
        prodName.setText(getIntent().getStringExtra("ProductName"));
        prodPrice.setText(getIntent().getStringExtra("ProductPrice"));
        if (getIntent().getStringExtra("ProductBrand").isEmpty())
        {
            TextView text=findViewById(R.id.textview2);
            text.setVisibility(View.INVISIBLE);
        }
        else
        {
            Brand.setText(getIntent().getStringExtra("ProductBrand"));
        }

        //adding to wishlist
        addWish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                show();
            }
        });
    }

        //Function for binding various XML components
        private void bindingVariables(){

            prodName = findViewById(R.id.prodName);
            prodPrice = findViewById(R.id.prodPrice);
            Brand = findViewById(R.id.Brand);
            prodImage = findViewById(R.id.prodImage);
            addWish = findViewById(R.id.addwishlist);
        }

        public void show(){
            d.setContentView(R.layout.quantity);
            Button button = (Button) d.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText quant;
                    quant = (EditText)d.findViewById(R.id.editText);
                    product = getIntent().getStringExtra("ProductName");

                    try {
                        quantity = Integer.parseInt(quant.getText().toString());
                        if(quantity==0){
                            throw new Exception();
                        }
                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ProductView.this);
                        databaseAccess.open();
                        databaseAccess.insertWishlist(product, quantity);
                        databaseAccess.close();
                        d.dismiss();
                        Toast toast = Toast.makeText(getApplicationContext(), "Item added to wishlist", Toast.LENGTH_SHORT);
                        toast.show();
                    }catch(Exception e){
                        Toast toast = Toast.makeText(getApplicationContext(), "Quanity cannot be null", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });

            d.show();
        }
    };
