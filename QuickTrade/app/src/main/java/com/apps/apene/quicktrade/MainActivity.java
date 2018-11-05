package com.apps.apene.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    protected ImageView mCategoryMotor = null;
    protected ImageView mCategoryHome = null;
    protected ImageView mCategoryTech = null;
    protected Button mButtonUpload = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCategoryMotor = findViewById(R.id.iv_category_motor);
        mCategoryHome = findViewById(R.id.iv_category_home);
        mCategoryTech = findViewById(R.id.iv_category_tech);
        mButtonUpload = findViewById(R.id.bt_main_upload);

        mCategoryMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mCategoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mCategoryTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
