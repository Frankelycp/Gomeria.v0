package com.g.gomeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.g.gomeria.NuevaLLanta.AddItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem;
    Button buttonAddItem2;
    Button buttonAddItem3;
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

            Intent intent = new Intent(getApplicationContext(),Cambio.class);
            startActivity(intent);
        }

    }
}







/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button stock = (Button) findViewById(R.id.STOCK);
        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Stock.class);
                startActivityForResult(intent, 0);
            }
        });

        Button nueva = (Button) findViewById(R.id.NUEVA);
        nueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Nueva.class);
                startActivityForResult(intent, 0);
            }
        });


        Button cambio = (Button) findViewById(R.id.CAMBIO);
        cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Cambio.class);
                startActivityForResult(intent, 0);
            }
        });


    }


}
*/