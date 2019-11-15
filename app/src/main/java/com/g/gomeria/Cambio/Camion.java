package com.g.gomeria.Cambio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.g.gomeria.NuevaLLanta.Patente;
import com.g.gomeria.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;


public class Camion extends AppCompatActivity {

    private String URLstring = "https://script.googleusercontent.com/a/macros/transchemicalsa.com.ar/echo?user_content_key=g2QYajePlcCa9d_YJKEc5TtaE_lPGy1AIdGBiVa2BIZfv0IFLoqWFb30c6ZIW1zHpd_-kXkNCb4ilPMKjPhCUdZQ6f64d5bBOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDI_4Q-pKkLe7MNxJdrWyCsJV3ddGLkNmSAGz9x4Gmo7upEGACIRS2gvVvOc8yrT9pLX6HqBmn5ueosw75brm8XOxguoQHxo4t911Uq9f875pC0PWKWrtrCndNuyBbD-5uwbiTIP26maNIeZ2OIybTOY7sOArVtvnmvObqsO96hwSMyFZdnCJNOo-s-rPDCSt8JuS3Mld3bLg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";


    private ArrayList<Patente> PatenteArrayList;

    private ArrayList<Patente> patenteArrayList;

    private ArrayList<String> patente = new ArrayList<>();



    EditText editTextPatente;


    private Spinner spinnerPatente;

    Button camion ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gomeria);

        spinnerPatente = findViewById(R.id.spinnerPatente);

        camion = (Button)findViewById(R.id.camion);

        editTextPatente = findViewById(R.id.patente);

        retrieveJSON();



    }


    private void retrieveJSON() {


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);


                        try {
                            JSONObject obj = new JSONObject(response);
                            patenteArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Patente playerMode = new Patente ();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode.setPatente(dataobj.getString("PATENTE"));
                                patenteArrayList.add(playerMode);
                            }
                            for (int i = 0; i < patenteArrayList.size(); i++) {
                                patente.add(patenteArrayList.get(i).getPatente());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Camion.this, simple_spinner_item, patente);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerPatente.setAdapter(spinnerArrayAdapter);
                            spinnerPatente.setSelection(spinnerArrayAdapter.NO_SELECTION, false);


                            spinnerPatente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    editTextPatente.setText((CharSequence) spinnerPatente.getSelectedItem());
                                    ((TextView) view).setText(null);







                                }








                                public void onNothingSelected(AdapterView<?> parent) {
                                }


                            });


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


                }

        );


        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest1);


    }

}