package com.g.gomeria.CambioCubierta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;



import androidx.appcompat.app.AppCompatActivity;

import com.g.gomeria.R;


public class Unidad extends AppCompatActivity implements View.OnClickListener {
    Button CAMION ;
    Button SEMI ;
    ProgressDialog progressDoalog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unidad);

        CAMION = (Button)findViewById(R.id.unidad_camion);
        CAMION.setOnClickListener(this);

        SEMI = (Button)findViewById(R.id.unidad_semi);
        SEMI.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {


        if(v==CAMION){




            Intent intent = new Intent(getApplicationContext(), Camion_origen.class);
            startActivity(intent);



        }

        if(v==SEMI){



            Intent intent = new Intent(getApplicationContext(),Semi_origen.class);
            startActivity(intent);
        }



    }


}