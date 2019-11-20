package com.g.gomeria.Cambio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.g.gomeria.MainActivity;
import com.g.gomeria.NuevaLLanta.AddItem;
import com.g.gomeria.NuevaLLanta.Patente;
import com.g.gomeria.NuevaLLanta.Semi;
import com.g.gomeria.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;


public class Gomeria extends AppCompatActivity {

    private String URLstring = "https://script.googleusercontent.com/a/macros/transchemicalsa.com.ar/echo?user_content_key=g2QYajePlcCa9d_YJKEc5TtaE_lPGy1AIdGBiVa2BIZfv0IFLoqWFb30c6ZIW1zHpd_-kXkNCb4ilPMKjPhCUdZQ6f64d5bBOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDI_4Q-pKkLe7MNxJdrWyCsJV3ddGLkNmSAGz9x4Gmo7upEGACIRS2gvVvOc8yrT9pLX6HqBmn5ueosw75brm8XOxguoQHxo4t911Uq9f875pC0PWKWrtrCndNuyBbD-5uwbiTIP26maNIeZ2OIybTOY7sOArVtvnmvObqsO96hwSMyFZdnCJNOo-s-rPDCSt8JuS3Mld3bLg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";


    private ArrayList<Patente> PatenteArrayList;
    private ArrayList<Patente> SemiArrayList;



    //SE DECLARAN LAS LISTAS DE LOS ARRAY PARA LOS ADAPTERS
    private ArrayList<Patente> patenteArrayList;
    private ArrayList<Semi> semiArrayList;

//SE DECLARAN LAS LISTAS PARA LOS ARRAY

    private ArrayList<String> patente = new ArrayList<>();
    private ArrayList<String> semi = new ArrayList<>();




// SE DECLARAN LOS EDITVIEW

    EditText editTextPatente;
    EditText et_semi;

    EditText et_gomeria;

    EditText editTextfuego;

    EditText ediTextOdo_destino;



    //SE DECLARAN LOS SPINNER
    private Spinner spinnerPatente;
    private Spinner spinnerSemi;

//SE DECLARAN LOS BOTONES
    Button camion ;
    Button b_semi ;
    Button enviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gomeria);


//LE INDICO A LOS SPINER DE DONDE TOMAR EL VALOR EN EL XML
        spinnerPatente = findViewById(R.id.spinnerPatente);
        spinnerSemi = findViewById(R.id.spinnerSemi);

//BOTONES PARA SELECIONAR A QUIEN SE LE CAMBIARA
        camion = (Button)findViewById(R.id.camion);
        b_semi  = (Button)findViewById(R.id.B_SEMI);
        enviar = (Button)findViewById(R.id.enviar) ;

// CASILLAS DE TEXTO PARA LOS SPINER
        editTextPatente = findViewById(R.id.patente);
        et_semi = findViewById(R.id.ET_SEMI);
        et_gomeria = findViewById(R.id.editGomeria);
        editTextfuego = findViewById(R.id.n_fuego);
        ediTextOdo_destino = findViewById(R.id.n_odo);



        editTextPatente.setVisibility(View.INVISIBLE);
        et_semi.setVisibility(View.INVISIBLE);
        spinnerSemi.setVisibility(View.INVISIBLE);
        spinnerPatente.setVisibility(View.INVISIBLE);
        editTextfuego.setVisibility(View.INVISIBLE);
        ediTextOdo_destino.setVisibility(View.INVISIBLE);







//OBTENGO EL JSON
        retrieveJSON();


        camion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    editTextPatente.setVisibility(View.VISIBLE);
                    et_semi.setVisibility(View.INVISIBLE);
                    spinnerSemi.setVisibility(View.INVISIBLE);
                    spinnerPatente.setVisibility(View.VISIBLE);
                editTextfuego.setVisibility(View.VISIBLE);
                ediTextOdo_destino.setVisibility(View.VISIBLE);


            }
        });



        b_semi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                editTextPatente.setVisibility(View.INVISIBLE);
                et_semi.setVisibility(View.VISIBLE);
                spinnerSemi.setVisibility(View.VISIBLE);
                spinnerPatente.setVisibility(View.INVISIBLE);

                editTextfuego.setVisibility(View.INVISIBLE);
                editTextfuego.setVisibility(View.INVISIBLE);


            }






        });

        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if ((ediTextOdo_destino.getText().toString().trim().length() !=0) && (editTextfuego.getText().toString().trim().length() !=0) && (editTextPatente.getText().toString().trim().length() != 0 )){

                    addItemToSheet();
                }
                else
                {

                    Toast.makeText(Gomeria.this, "Ningun Campo puede quedar vacio", Toast.LENGTH_LONG).show(); //Correcto

                }


            }


        });












    }

//CLASE PARA EL JSON DONDE INVOCA LOS DATOS DE GOOGLE

    private void retrieveJSON() {


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);

// INTENTA BUSCAR LOS DATOS SELECCIONADOS EN EL JSON POR EL NOMBRE DE LA HOJA Y POR EL NOMBRE DE LA FILA  LOS GUARDA EN LOS ARRAY DEL ADAPTER Y EL ARRAY COMUN

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

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Gomeria.this, simple_spinner_item, patente);


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


                        try {
                            JSONObject obj = new JSONObject(response);
                            semiArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Semi playerMode1 = new Semi ();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode1.setSemi(dataobj.getString("SEMI"));
                                semiArrayList.add(playerMode1);
                            }
                            for (int i = 0; i < semiArrayList.size(); i++) {
                                semi.add(semiArrayList.get(i).getSemi());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Gomeria.this, simple_spinner_item, semi);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerSemi.setAdapter(spinnerArrayAdapter);
                            spinnerSemi.setSelection(spinnerArrayAdapter.NO_SELECTION, false);



                            spinnerSemi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    et_semi.setText((CharSequence) spinnerSemi.getSelectedItem());
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



    // A PARTIR DE ACA ES PARA LLENAR LOS DATOS


    private void addItemToSheet() {



        final ProgressDialog loading = ProgressDialog.show(this, "Cambiando Cubierta", "Por favor Espere");
       final String n_fuego = editTextfuego.getText().toString().trim();
       // final String uso = editTextItemUso.getText().toString().trim();
       final String p_origen = et_gomeria.getText().toString().trim();

        final String odo_origen = et_gomeria.getText().toString().trim();
        final String posc_origen = et_gomeria.getText().toString().trim();

        final String p_destino = editTextPatente.getText().toString().trim();


       final String odo_destino = ediTextOdo_destino.getText().toString().trim();
       // final String posc_destino = editTextPosc_destino.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw_75KoOxUf-9hYM-KGWHXnxgo6wmqvvdaupIynTRKt_HQt9rlE/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(Gomeria.this, response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params


                parmas.put("action", "addItem");



                parmas.put("n_fuego", n_fuego);

                parmas.put("p_origen", p_origen);
                parmas.put("odo_origen", odo_origen);
                parmas.put("posc_origen", posc_origen);
                parmas.put("p_destino", p_destino);
                parmas.put("odo_destino", odo_destino);


                return parmas;
            }
        };

        int socketTimeOut = 30000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }
}