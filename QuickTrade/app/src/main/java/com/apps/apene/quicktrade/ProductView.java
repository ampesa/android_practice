package com.apps.apene.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductView extends AppCompatActivity {

    protected ImageView mProductImage = null;
    protected TextView mProductDescription = null;
    protected TextView mProductPrice = null;
    protected TextView mProductCountry = null;
    protected TextView mProductZip = null;
    protected Button mButtonEdit = null;
    protected Button mButtonBuy = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mProductImage = findViewById(R.id.iv_product_image);
        mProductDescription = findViewById(R.id.tv_product_description);
        mProductPrice = findViewById(R.id.tv_product_price);
        mProductCountry = findViewById(R.id.tv_product_country);
        mProductZip = findViewById(R.id.tv_product_zip);
        mButtonBuy = findViewById(R.id.bt_product_buy);
        mButtonEdit = findViewById(R.id.bt_product_edit);

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
