package com.g.gomeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tutorialspoint7.myapplication.R;

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
