package com.g.gomeria.CambioCubierta;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.g.gomeria.R;




public class Pop extends Activity implements View.OnClickListener {
    ImageButton a1,a2;

    ImageButton b1,b2,b3,b4;
    ImageButton c1,c2,c3,c4,c11,c22;
    EditText ediTextPosicion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {







        super.onCreate(savedInstanceState);
        setContentView(R.layout.cubiertas_camion);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
    }

    @Override
    public void onClick(View v) {
        if (v == a1) {
            ediTextPosicion.setText("A1");
            setContentView(R.layout.gomeria);

        }
        if (v == a2) {
            ediTextPosicion.setText("A2");


        }
        if (v == b1) {
            ediTextPosicion.setText("B1");

        }
        if (v == b2) {

            ediTextPosicion.setText("B2");

        }

        if (v == b3) {

            ediTextPosicion.setText("B3");

        }
        if (v == b4) {

            ediTextPosicion.setText("B4");

        }

        if (v == c1) {

            ediTextPosicion.setText("C1");

        }
        if(v==c2){

            ediTextPosicion.setText("C2");

        }
        if(v==c3){
            ediTextPosicion.setText("C3");

        }
        if(v==c4){
            ediTextPosicion.setText("C4");

        }
        if(v==c11){
            ediTextPosicion.setText("C11");

        }
        if(v==c22){
            ediTextPosicion.setText("C22");


        }
    }
}
