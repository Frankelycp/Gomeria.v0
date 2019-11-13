package com.g.gomeria;

import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tutorialspoint7.myapplication.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Cambio extends AppCompatActivity {

    private EditText fuegoInputField;
    private EditText medidaInputField;
    private EditText tipoInputField;
    private EditText marcaInputField;
    private EditText observacionesInputField;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio);


        fuegoInputField = (EditText) findViewById(R.id.fuego_input);
        medidaInputField = (EditText) findViewById(R.id.medida_input);
        tipoInputField = (EditText) findViewById(R.id.tipo_input);
        marcaInputField = (EditText) findViewById(R.id.marca_input);
        observacionesInputField = (EditText) findViewById(R.id.observaciones_input);


        findViewById(R.id.submit_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateInput();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    private void validateInput() { // Validate text input

        if (fuegoInputField.getText().toString().trim().length() == 0 && medidaInputField.getText().toString().trim().length() == 0 && tipoInputField.getText().toString().trim().length() == 0 && marcaInputField.getText().toString().trim().length() == 0 && observacionesInputField.getText().toString().trim().length() == 0) {
           fuegoInputField.setError("Ingrese Numero de Fuego!");
            medidaInputField.setError("Ingrese Medida!");
            tipoInputField.setError("Ingrese Tipo!");
            marcaInputField.setError("Ingrese Marca!");
            observacionesInputField.setError("Ingrese Alguna Observacion!");
            Toast.makeText(Cambio.this, "No puede quedar campos vacios!", Toast.LENGTH_LONG).show();

        }
    }



    private void sendData() { // Send feedback to Google Spreadsheet if text input is valid

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/1FAIpQLSdpbhf5vLm9-1spc3O8LIVtVju1_vnVtOulr5ELAFYopkuqIQ/formResponse") // Your spreadsheet URL
                .build();
        final SpreadsheetWebService spreadsheetWebService = retrofit.create(SpreadsheetWebService.class);

        String fuegoInput = fuegoInputField.getText().toString();
        String medidaInput = medidaInputField.getText().toString();
        String tipoInput = tipoInputField.getText().toString();
        String marcaInput = marcaInputField.getText().toString();
        String observacioneskInput = observacionesInputField.getText().toString();



        Call<Void> feedbackCall = spreadsheetWebService.feedbackSend(fuegoInput, medidaInput, tipoInput, marcaInput,observacioneskInput);
        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("XXX", "Submitted. " + response);
                Toast.makeText(Cambio.this,"Completado",Toast.LENGTH_LONG).show();
                // Clear all fields after submitting
                fuegoInputField.setText("");
                medidaInputField.setText("");
                tipoInputField.setText("");
                marcaInputField.setText("");
                observacionesInputField.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("XXX", "Fallo", t);
                Toast.makeText(Cambio.this,"Hay Algun Error!",Toast.LENGTH_LONG).show();
            }
        });
    }

}
