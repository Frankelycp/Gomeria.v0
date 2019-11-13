package com.g.gomeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorialspoint7.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.g.gomeria.NuevaLLanta.Marca;
import com.g.gomeria.NuevaLLanta.Patente;
import com.g.gomeria.NuevaLLanta.Semi;
import com.g.gomeria.NuevaLLanta.Tamaño;
import com.g.gomeria.NuevaLLanta.Posicion;


import static android.R.layout.simple_spinner_item;

public class Nueva extends AppCompatActivity {


    private String URLstring = "https://script.googleusercontent.com/macros/echo?user_content_key=YQMZwYrNDJcGFQzfnEVo3d7IJZIsnmsCwvmHe6KPGFIHr0tsNcjoa_aWVlhfC7_esWKSO-PWHk5RSel7BhrheVvvQOMVqtBVOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUDFLHTWrhB1wewdLro56xPVSWbDL2OlNarWDIGK14Cd465L2Ug6EwLgz7NxCqgIKm5y7FLqOV0TnXnPf7ksCyxQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
    private ArrayList<GoodModel> goodModelArrayList;

    private ArrayList<Patente> patenteArrayList,semiArrayList,marcaArrayList,posicionArrayList,tamañoArrayList;


    private ArrayList<String> names  = new ArrayList<String>();
    private ArrayList<String> patente = new ArrayList<String>();
    private ArrayList<String> semi = new ArrayList<String>();
    private ArrayList<String> posicion = new ArrayList<String>();
    private ArrayList<String> marca = new ArrayList<String>();
    private ArrayList<String> tamaño = new ArrayList<String>();



    private Spinner spinnerPosicion;
    private Spinner spinnerPatente;
    private Spinner spinnerSemi;
    private Spinner spinnerMarca;
    private Spinner spinnerTamaño;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva);

        spinnerPosicion = findViewById(R.id.spinnerPosicion);
        spinnerPatente = findViewById(R.id.spinnerCamion);
        spinnerSemi = findViewById(R.id.spinnerSemi);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerTamaño = findViewById(R.id.spinnerTamaño);


        retrieveJSON();

    }

    private void retrieveJSON() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);

                            //PATENTE

                            try {
                                JSONObject obj = new JSONObject(response);
                                patenteArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("Stock");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    Patente playerModel = new Patente();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setPatente(dataobj.getString("PATENTE"));
                                    patenteArrayList.add(playerModel);
                                }
                                for (int i = 0; i < patenteArrayList.size(); i++){
                                    patente.add(patenteArrayList.get(i).getPatente().toString());
                                }
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Nueva.this, simple_spinner_item, patente);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spinnerPatente.setAdapter(spinnerArrayAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }






}

