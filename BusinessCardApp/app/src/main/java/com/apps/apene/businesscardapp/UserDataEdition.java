package com.apps.apene.businesscardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class UserDataEdition extends AppCompatActivity {

    protected EditText mUserNameEditText = null;
    protected EditText mPositionEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_edition);

        mUserNameEditText = findViewById(R.id.et_user_name);
        mPositionEditText = findViewById(R.id.et_position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            // El usuario quiere voler a la actividad principal
            String userName = mUserNameEditText.getText().toString();
            String position = mPositionEditText.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("username", userName);
            intent.putExtra( "position", position);
            setResult(RESULT_OK, intent);
            finish();
        }
        return true;
    }
}
