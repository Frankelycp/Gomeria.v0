package com.g.gomeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.g.gomeria.CambioCubierta.Gomeria;
import com.g.gomeria.NuevaCubierta.AddItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem;
    Button buttonAddItem2;
    Button buttonAddItem3;

    Button gomeria;
    Button camion;
    Button regresar;

    TextView Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddItem = (Button)findViewById(R.id.NUEVA);
        buttonAddItem.setOnClickListener(this);

        buttonAddItem2 = (Button)findViewById(R.id.CAMBIO);
        buttonAddItem2.setOnClickListener(this);

        buttonAddItem3 = (Button)findViewById(R.id.STOCK);
        buttonAddItem3.setOnClickListener(this);

        gomeria = (Button)findViewById(R.id.gomeria);
        gomeria.setOnClickListener(this);

       // camion = (Button)findViewById(R.id.camion);
        //camion.setOnClickListener(this);

        regresar = (Button)findViewById(R.id.atras);
        regresar.setOnClickListener(this);


        Text = findViewById(R.id.text);
        Text.setVisibility(View.INVISIBLE);





        gomeria.setVisibility(View.GONE);
       // camion.setVisibility(View.GONE);
        regresar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {

        if(v==buttonAddItem){

            Intent intent = new Intent(getApplicationContext(), AddItem.class);
            startActivity(intent);
        }

        if(v==buttonAddItem3){

            Intent intent = new Intent(getApplicationContext(),Stock.class);
            startActivity(intent);
        }

        if(v==buttonAddItem2){

            gomeria.setVisibility(View.VISIBLE);
            camion.setVisibility(View.VISIBLE);
            buttonAddItem3.setVisibility(View.GONE);
            buttonAddItem.setVisibility(View.GONE);
            regresar.setVisibility(View.VISIBLE);
            Text.setVisibility(View.VISIBLE);


            //Intent intent = new Intent(getApplicationContext(), Gomeria.class);
           // startActivity(intent);
        }


        if(v==regresar){

            gomeria.setVisibility(View.GONE);
            camion.setVisibility(View.GONE);
            buttonAddItem3.setVisibility(View.VISIBLE);
            buttonAddItem.setVisibility(View.VISIBLE);
            regresar.setVisibility(View.GONE);
            Text.setVisibility(View.INVISIBLE);


        }

        if(v==gomeria){

            Intent intent = new Intent(getApplicationContext(), Gomeria.class);
            startActivity(intent);

        }



    }
}




