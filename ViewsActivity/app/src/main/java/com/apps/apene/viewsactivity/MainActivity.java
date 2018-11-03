package com.apps.apene.viewsactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

// Hacemos que la lcase implemente la interfaz onClickListener para facilitar el trabajo con los listeners
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Creamos los elementos gráficos
    protected Button mRedBackground = null;
    protected Button mBlackFont = null;
    protected CheckBox mShowText = null;
    protected TextView mSecretMessage = null;
    protected TextView mLongClick = null;
    protected RelativeLayout mRatingLayout = null;
    protected RatingBar mRatingBar = null;
    protected TextView mRatingText = null;
    protected Switch mRateNoRate = null;
    protected TextView mProgressText = null;
    protected ProgressBar mProgressBar = null;
    protected SeekBar mSeekBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos los elementos gráficos
        mRedBackground = findViewById(R.id.bt_red_background);
        mBlackFont = findViewById(R.id.bt_black_font);
        mShowText = findViewById(R.id.cb_show_text);
        mSecretMessage = findViewById(R.id.tv_hidden_message);
        mLongClick = findViewById(R.id.tv_ask_long_click);
        mRatingLayout = findViewById(R.id.rl_rating_bar);
        mRatingBar = findViewById(R.id.rb_five_stars);
        mRatingText =findViewById(R.id.tv_rating_value);
        mRateNoRate = findViewById(R.id.sw_rate);
        mProgressText = findViewById(R.id.tv_progress);
        mProgressBar = findViewById(R.id.pb_progress_bar);
        mSeekBar = findViewById(R.id.sb_seek_bar);

        // Establecemos valores iniciales de los elementos cambiantes
        mRatingLayout.setBackgroundColor(Color.TRANSPARENT);
        mBlackFont.setTextColor(Color.BLACK);
        mSecretMessage.setVisibility(View.INVISIBLE);
        mRatingBar.setIsIndicator(true);
        mRateNoRate.setChecked(false);

        // Asignamos los listener convencionales
        mRedBackground.setOnClickListener(this);
        mBlackFont.setOnClickListener(this);
        mShowText.setOnClickListener(this);
        mLongClick.setOnClickListener(this);
        mRateNoRate.setOnClickListener(this);

        // Asignamos el long click listener al TextView correspondiente
        mLongClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast shortclick = Toast.makeText(getApplicationContext(), R.string.show_long, Toast.LENGTH_SHORT);
                shortclick.show();
                return false;
            }
        });

        // Asignamos el listener al RatingBar
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRatingText.setText("["+(int)rating+"/5]");
            }
        });

        // Asignamos listener al botón Switch. Si está activado, podremos votar si no esta activado, no podremos
        mRateNoRate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mRateNoRate.isChecked()){
                    // Switch activado = RatingBar activo y texto del Switch "Votar"
                    mRatingBar.setIsIndicator(false);
                    mRateNoRate.setText(R.string.rate_true);
                } else{
                    // Switch desactivado = RatingBar inactivo y texto "No Votar"
                    mRatingBar.setIsIndicator(true);
                    mRateNoRate.setText(R.string.rate_false);
                }
            }
        });

        // Asignamos el listener al SeekBar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // ProgressBar seguirá el progreso de SeekBar y el texto mostrará el progreso en %
                mProgressBar.setProgress(progress);
                mProgressText.setText("" + progress +"%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }

    // Agrupamos la gestión de los clicks sencillos
    @Override
    public void onClick(View v) {
        // Utilizamos un int para establecer qué hacer en función del click
        int itemView = v.getId();

        // Si se ha pulsado el botón FONDO ROJO
        if (itemView == mRedBackground.getId()) {
            if (((ColorDrawable)mRatingLayout.getBackground()).getColor() == Color.TRANSPARENT) {
                // Si el color del fondo era transparente lo pasamos a rojo
                mRatingLayout.setBackgroundColor(Color.RED);
            }else {
                // en cualquier otro caso (o sea, si es rojo) lo pasamosa transparente
                mRatingLayout.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (itemView == mBlackFont.getId()) {// Si se ha pulsado el botón LETRAS NEGRAS
            if (mBlackFont.getCurrentTextColor() == Color.BLACK) {
                // Si el color de las letras es negro
                mBlackFont.setTextColor(Color.RED);
            }else {
                // en cualquier otro caso pasamos las letras a negro
                mBlackFont.setTextColor(Color.BLACK);
            }
        }else if (itemView == mShowText.getId()) {// Si se marca el ChecBox
            if (mSecretMessage.getVisibility() == View.INVISIBLE) {
                // Si el mensaje no está visible
                mSecretMessage.setVisibility(View.VISIBLE);
            }else {
                // en cualquier otro caso lo pasamosa a invisible
                mSecretMessage.setVisibility(View.INVISIBLE);
            }
        }else if (itemView == mLongClick.getId()){ // Si pulsamos texto con click corto
            Toast shortclick = Toast.makeText(getApplicationContext(), R.string.show_short, Toast.LENGTH_SHORT);
            shortclick.show();
        }
    }

}
