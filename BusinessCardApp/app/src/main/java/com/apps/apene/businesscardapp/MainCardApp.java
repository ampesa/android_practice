package com.apps.apene.businesscardapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainCardApp extends AppCompatActivity implements View.OnClickListener {

    /* Creamos los atributos que apuntarán a las vistas que hemos creado en el xml
     *  Los iniciamos a null porque queremos que apunten a las vistas cada vez que se
     *  cree la actividad, por tanto les asignaremos las vistas dentro del método onCreate
     *  */
    //protected EditText mUserNameEdit = null;
    //protected EditText mJobPositionEdit = null;
    protected ImageView mProfilePhoto = null;
    protected Button mEditButton = null;
    protected Button mShareButton =null;
    protected TextView mUserNameTextView = null;
    protected TextView mPositionTextView = null;

    protected int REQUEST_FOR_CAMERA = 1;
    protected int REQUEST_EDIT_DATA = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card_app);

        // Asignamos a los atributos sus correspondientes vistas

        mUserNameTextView = findViewById(R.id.tv_user_name);
        mPositionTextView = findViewById(R.id.tv_position);
        mProfilePhoto = findViewById(R.id.iv_user_photo);
        mEditButton = findViewById(R.id.bt_save_edit);
        mShareButton = findViewById(R.id.bt_share);

        mEditButton.setOnClickListener(this);
        mShareButton.setOnClickListener(this);
        mProfilePhoto.setOnClickListener(this);

    }

    protected void shareData (){
        Intent intentToShare = new Intent();
        intentToShare.setAction(Intent.ACTION_SEND);
        intentToShare.setType("text/plain");
        intentToShare.putExtra(Intent.EXTRA_TEXT, "Hola soy, " +
                mUserNameTextView.getText().toString() + " por favor, añádeme a tus redes sociales!");
        if (intentToShare.resolveActivity(getPackageManager()) != null) {
            // Hay una aplicación capaz de resolver la petición
            startActivity(intentToShare);
        } else {
            // No hay aplicación para resolver esta petición
            Toast showNoApp = Toast.makeText(this, getString(R.string.no_app_available), Toast.LENGTH_LONG);
            showNoApp.show();
        }
    }

    protected void takePic () {
        Intent intentToTakePic = new Intent();
        intentToTakePic.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intentToTakePic.resolveActivity(getPackageManager()) != null) {
            // Hay una aplicación de cámara
            startActivityForResult(intentToTakePic, REQUEST_FOR_CAMERA);
        }else {
            // No hay aplicación para resolver esta petición
            Toast showNoApp = Toast.makeText(this, getString(R.string.no_camera_app), Toast.LENGTH_LONG);
            showNoApp.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode  == REQUEST_FOR_CAMERA){
            if (resultCode == RESULT_OK){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                mProfilePhoto.setImageBitmap(image);

            }
        }else if (requestCode == REQUEST_EDIT_DATA && resultCode == RESULT_OK){
            mUserNameTextView.setText(data.getStringExtra("username"));
            mPositionTextView.setText(data.getStringExtra("position"));
        }
    }

    @Override
    public void onClick(View v) {
        // Capturamos el Id del view pulsado
        int viewId = v.getId();

        // Si el id del view pulsado coincide con el del botón SaveEdit hacemos lo siguiente
        if(viewId == mEditButton.getId()){
            // Click en el Edit button lanza la actividad UserDataEdition
            Intent newActivity = new Intent(this, UserDataEdition.class);
            startActivityForResult(newActivity, REQUEST_EDIT_DATA);

        }else if (viewId == mShareButton.getId()){
            // Se ha hecho click en el botón Share
            shareData();
        }else if (viewId == mProfilePhoto.getId()) {
            // Necesitamos tomar la foto con la cámara
            takePic();
        }
    }
}
