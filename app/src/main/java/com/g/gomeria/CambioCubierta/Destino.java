package com.g.gomeria.CambioCubierta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.g.gomeria.Clases.ConfS;
import com.g.gomeria.Clases.ConfT;
import com.g.gomeria.Clases.Estado;
import com.g.gomeria.Clases.Patente;
import com.g.gomeria.Clases.Semi;
import com.g.gomeria.Clases.Uso;
import com.g.gomeria.MainActivity;
import com.g.gomeria.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;


public class Destino extends AppCompatActivity implements View.OnClickListener {



    private ProgressDialog pDialog;


    private String URLstring = "https://script.googleusercontent.com/a/macros/transchemicalsa.com.ar/echo?user_content_key=g2QYajePlcCa9d_YJKEc5TtaE_lPGy1AIdGBiVa2BIZfv0IFLoqWFb30c6ZIW1zHpd_-kXkNCb4ilPMKjPhCUdZQ6f64d5bBOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDI_4Q-pKkLe7MNxJdrWyCsJV3ddGLkNmSAGz9x4Gmo7upEGACIRS2gvVvOc8yrT9pLX6HqBmn5ueosw75brm8XOxguoQHxo4t911Uq9f875pC0PWKWrtrCndNuyBbD-5uwbiTIP26maNIeZ2OIybTOY7sOArVtvnmvObqsO96hwSMyFZdnCJNOo-s-rPDCSt8JuS3Mld3bLg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";



    private ArrayList<Patente> PatenteArrayList;
    private ArrayList<Semi> SemiArrayList;

    private ArrayList<ConfT> ConfTArrayList;
    private ArrayList<ConfS> ConfiSArrayList;





    //SE DECLARAN LAS LISTAS DE LOS ARRAY PARA LOS ADAPTERS
    private ArrayList<Patente> patenteArrayList;
    private ArrayList<Semi> semiArrayList;
    private ArrayList<ConfS> confSArrayList;
    private ArrayList<ConfT> confTArrayList;



//SE DECLARAN LAS LISTAS PARA LOS ARRAY

    private ArrayList<String> patente = new ArrayList<>();
    private ArrayList<String> semi = new ArrayList<>();
    private ArrayList<String> confT = new ArrayList<>();
    private ArrayList<String> confS = new ArrayList<>();

    Integer PosicionPatente ;
    Integer PosicionSemi ;






    TextView Fuego_origen;
    TextView Estado_origen;
    TextView Uso_origen;
    TextView Patente_origen;
    TextView Odo_origen;
    TextView Pos_origen;


    EditText editTextPatente;
    EditText ediTextOdo_destino;
    EditText ediTextPosicion;
    EditText editTextConfigT;
    EditText editTextConfigS;



    Button camion ;
    Button b_semi ;
    Button enviar;
    Button gomeria ;


    private Spinner spinnerPatente;
    private Spinner spinnerSemi;
    private Spinner spinnerConfiguracionT;
    private Spinner spinnerConfiguracionS;





    ImageButton a1,a2;

    ImageButton b1,b2,b3,b4;
    ImageButton c1,c2,c3,c4,c11,c22;

    ImageButton d11,d22;
    ImageButton e11,e22;
    ImageButton f11,f22;

    ImageButton d1,d2,d3,d4;
    ImageButton e1,e2,e3,e4;
    ImageButton f1,f2,f3,f4;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destino);



        spinnerPatente = findViewById(R.id.spinnerPatente);
        spinnerSemi = findViewById(R.id.spinnerSemi);
        spinnerConfiguracionS = findViewById(R.id.spinnerConfigSemi);
        spinnerConfiguracionT = findViewById(R.id.spinnerConfigTrack);


        gomeria = (Button)findViewById(R.id.gomeria);
        gomeria.setOnClickListener(this);


        camion = (Button)findViewById(R.id.camion);
        camion.setOnClickListener(this);

        enviar = (Button)findViewById(R.id.enviar) ;
       enviar.setOnClickListener(this);


        b_semi  = (Button)findViewById(R.id.semidestino);
        b_semi.setOnClickListener(this);


        a1 = (ImageButton)findViewById(R.id.a1) ;
        a1.setOnClickListener(this);


        a2 = (ImageButton)findViewById(R.id.a2) ;
        a2.setOnClickListener(this);

        b1 = (ImageButton)findViewById(R.id.b1) ;
        b1.setOnClickListener(this);

        b3 = (ImageButton)findViewById(R.id.b3) ;
        b3.setOnClickListener(this);

        b2 = (ImageButton)findViewById(R.id.b2);
        b2.setOnClickListener(this);

        b4 = (ImageButton)findViewById(R.id.b4) ;
        b4.setOnClickListener(this);


        c1 = (ImageButton)findViewById(R.id.c1) ;
        c1.setOnClickListener(this);

        c2 = (ImageButton)findViewById(R.id.c2) ;
        c2.setOnClickListener(this);

        c3 = (ImageButton)findViewById(R.id.c3) ;
        c3.setOnClickListener(this);

        c4 = (ImageButton)findViewById(R.id.c4) ;
        c4.setOnClickListener(this);

        c11 = (ImageButton)findViewById(R.id.c11) ;
        c11.setOnClickListener(this);
        c22 = (ImageButton)findViewById(R.id.c22) ;
        c22.setOnClickListener(this);


        e1 = (ImageButton)findViewById(R.id.e1) ;
        e1.setOnClickListener(this);


        e2 = (ImageButton)findViewById(R.id.e2) ;
        e2.setOnClickListener(this);

        e3 = (ImageButton)findViewById(R.id.e3) ;
        e3.setOnClickListener(this);

        e4 = (ImageButton)findViewById(R.id.e4) ;
        e4.setOnClickListener(this);

        f1 = (ImageButton)findViewById(R.id.f1);
        f1.setOnClickListener(this);

        f2 = (ImageButton)findViewById(R.id.f2) ;
        f2.setOnClickListener(this);


        f3 = (ImageButton)findViewById(R.id.f3) ;
        f3.setOnClickListener(this);

        f4 = (ImageButton)findViewById(R.id.f4) ;
        f4.setOnClickListener(this);

        d1 = (ImageButton)findViewById(R.id.d1) ;
        d1.setOnClickListener(this);

        d2 = (ImageButton)findViewById(R.id.d2) ;
        d2.setOnClickListener(this);

        d3 = (ImageButton)findViewById(R.id.d3) ;
        d3.setOnClickListener(this);
        d4 = (ImageButton)findViewById(R.id.d4) ;
        d4.setOnClickListener(this);



        d11 = (ImageButton)findViewById(R.id.d11) ;
        d11.setOnClickListener(this);
        d22 = (ImageButton)findViewById(R.id.d22) ;
        d22.setOnClickListener(this);

        e11 = (ImageButton)findViewById(R.id.e11) ;
        e11.setOnClickListener(this);
        e22 = (ImageButton)findViewById(R.id.e22) ;
        e22.setOnClickListener(this);


        f11 = (ImageButton)findViewById(R.id.f11) ;
        f11.setOnClickListener(this);
        f22 = (ImageButton)findViewById(R.id.f22) ;
        f22.setOnClickListener(this);



        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        c1.setVisibility(View.INVISIBLE);
        c2.setVisibility(View.INVISIBLE);
        c3.setVisibility(View.INVISIBLE);
        c4.setVisibility(View.INVISIBLE);
        c11.setVisibility(View.INVISIBLE);
        c22.setVisibility(View.INVISIBLE);



        d1.setVisibility(View.INVISIBLE);
        d2.setVisibility(View.INVISIBLE);
        d3.setVisibility(View.INVISIBLE);
        d4.setVisibility(View.INVISIBLE);
        f1.setVisibility(View.INVISIBLE);
        f2.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        f4.setVisibility(View.INVISIBLE);

        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);

        //3SS
        d11.setVisibility(View.INVISIBLE);
        d22.setVisibility(View.INVISIBLE);

        e11.setVisibility(View.INVISIBLE);
        e22.setVisibility(View.INVISIBLE);

        f11.setVisibility(View.INVISIBLE);
        f22.setVisibility(View.INVISIBLE);


        // CASILLAS DE TEXTO PARA LOS SPINER
        editTextPatente = findViewById(R.id.patente);
        ediTextOdo_destino = findViewById(R.id.n_odo);
        ediTextPosicion = findViewById(R.id.posicionCamion);
        editTextConfigT = findViewById(R.id.configT);
        editTextConfigS = findViewById(R.id.configS);








        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String fuego = getIntent().getStringExtra("fuego");
        String uso = getIntent().getStringExtra("uso");
        String estado = getIntent().getStringExtra("estado");
        String odo = getIntent().getStringExtra("odo");
        String patente = getIntent().getStringExtra("patente");
        String posicion = getIntent().getStringExtra("posicion");


        spinnerSemi.setVisibility(View.INVISIBLE);
        spinnerPatente.setVisibility(View.INVISIBLE);











//ocultar Para cambio a camiones
        editTextPatente.setVisibility(View.INVISIBLE);
        ediTextOdo_destino.setVisibility(View.INVISIBLE);
        ediTextPosicion.setVisibility(View.INVISIBLE);
        editTextConfigT.setVisibility(View.INVISIBLE);
        editTextConfigS.setVisibility(View.INVISIBLE);
        spinnerConfiguracionS.setVisibility(View.INVISIBLE);
        spinnerConfiguracionT.setVisibility(View.INVISIBLE);




        Fuego_origen = findViewById(R.id.fuego);
        Uso_origen = findViewById(R.id.uso);
      Estado_origen = findViewById(R.id.estado);
      Patente_origen = findViewById(R.id.p_origen);
      Odo_origen = findViewById(R.id.odo_origen);
      Pos_origen = findViewById(R.id.pos_origen);




        Fuego_origen.setText(fuego);
        Uso_origen.setText(uso);
        Estado_origen.setText(estado);
        Patente_origen.setText(patente);
        Odo_origen.setText(odo);
        Pos_origen.setText(posicion);



        displayLoader();
        retrieveJSON();


    }

    private void displayLoader(){
        pDialog = new ProgressDialog(Destino.this);
        pDialog.setMessage("Cargando Datos.. Espere un momento...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }



    private void retrieveJSON() {



        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URLstring,
            new Response.Listener<String>() {


                @Override
                public void onResponse(String response) {
                    pDialog.dismiss();


                    Log.d("strrrrr", ">>" + response);

                    try {
                        JSONObject obj = new JSONObject(response);
                        confSArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("datos");
                        for (int i = 0; i < dataArray.length(); i++) {
                            ConfS playerMode4 = new ConfS  ();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            playerMode4.setConfs(dataobj.getString("CONF_SEMI"));
                            confSArrayList.add(playerMode4);



                        }
                        for (int i = 0; i < confSArrayList.size(); i++) {
                            if (i == 0) {
                                confS.add(confSArrayList.get(i).getConfs());
                            }
                            else if ((!confSArrayList.get(i).getConfs().equals("")) && (i != 0)) {
                                confS.add(confSArrayList.get(i).getConfs());
                            }
                        }


                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Destino.this, simple_spinner_item, confS);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinnerConfiguracionS.setAdapter(spinnerArrayAdapter);
                        spinnerConfiguracionS.setSelection(spinnerArrayAdapter.NO_SELECTION, false);



                        spinnerConfiguracionS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                editTextConfigS.setText((CharSequence) spinnerConfiguracionS.getSelectedItem());
                                ((TextView) view).setText(null);






                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    //CONFIGURACION S




                    try {
                        JSONObject obj = new JSONObject(response);
                        confTArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("datos");
                        for (int i = 0; i < dataArray.length(); i++) {
                            ConfT playerMode5 = new ConfT  ();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            playerMode5.setConft(dataobj.getString("CONF_TRAC"));
                            confTArrayList.add(playerMode5);


                        }
                        for (int i = 0; i < confTArrayList.size(); i++) {
                            if (i == 0) {
                                confT.add(confTArrayList.get(i).getConft());
                            }
                            else if ((!confTArrayList.get(i).getConft().equals("")) && (i != 0)) {
                                confT.add(confTArrayList.get(i).getConft());
                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Destino.this, simple_spinner_item, confT);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinnerConfiguracionT.setAdapter(spinnerArrayAdapter);
                        spinnerConfiguracionT.setSelection(spinnerArrayAdapter.NO_SELECTION, false);

                        spinnerConfiguracionT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                                editTextConfigT.setText((CharSequence) spinnerConfiguracionT.getSelectedItem());

                                ((TextView) view).setText(null);


                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
// patente

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
                            if (i == 0) {
                                patente.add(patenteArrayList.get(i).getPatente());
                            }
                            else if ((!patenteArrayList.get(i).getPatente().equals("")) && (i != 0)) {
                                patente.add(patenteArrayList.get(i).getPatente());
                            }
                        }
                        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Destino.this, simple_spinner_item, patente);

                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinnerPatente.setAdapter(spinnerArrayAdapter);
                        spinnerPatente.setSelection(spinnerArrayAdapter.NO_SELECTION, false);

                        spinnerPatente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                PosicionPatente = pos;

                                spinnerConfiguracionT.setSelection(PosicionPatente);

                                //   Toast.makeText(Gomeria.this, (test), Toast.LENGTH_SHORT).show();
                                editTextPatente.setText((CharSequence) spinnerPatente.getSelectedItem());
                                ((TextView) view).setText(null);

                                String posicion =spinnerConfiguracionT.getSelectedItem().toString();

                                if (posicion.equals("1S-2D")){
                                    a1.setVisibility(View.VISIBLE);
                                    a2.setVisibility(View.VISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    b2.setVisibility(View.VISIBLE);
                                    b3.setVisibility(View.VISIBLE);
                                    b4.setVisibility(View.VISIBLE);
                                    c1.setVisibility(View.VISIBLE);
                                    c2.setVisibility(View.VISIBLE);
                                    c3.setVisibility(View.VISIBLE);
                                    c4.setVisibility(View.VISIBLE);
                                    c22.setVisibility(View.INVISIBLE);
                                    c11.setVisibility(View.INVISIBLE);
                                }if (posicion.equals("1S-1D-1SS")){
                                    a1.setVisibility(View.VISIBLE);
                                    a2.setVisibility(View.VISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    b2.setVisibility(View.VISIBLE);
                                    b3.setVisibility(View.VISIBLE);
                                    b4.setVisibility(View.VISIBLE);
                                    c1.setVisibility(View.INVISIBLE);
                                    c2.setVisibility(View.INVISIBLE);
                                    c3.setVisibility(View.INVISIBLE);
                                    c4.setVisibility(View.INVISIBLE);
                                    c11.setVisibility(View.VISIBLE);
                                    c22.setVisibility(View.VISIBLE);
                                }

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
                            if (i == 0) {
                                semi.add(semiArrayList.get(i).getSemi());
                            }
                            if ((!semiArrayList.get(i).getSemi().equals("")) && (i != 0)) {
                                semi.add(semiArrayList.get(i).getSemi());
                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Destino.this, simple_spinner_item, semi);


                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinnerSemi.setAdapter(spinnerArrayAdapter);
                        spinnerSemi.setSelection(spinnerArrayAdapter.NO_SELECTION, false);





                        spinnerSemi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int posi, long id) {

                                PosicionSemi = posi;
                                spinnerConfiguracionS.setSelection(PosicionSemi);


                                editTextPatente.setText((CharSequence) spinnerSemi.getSelectedItem());
                                ((TextView) view).setText(null);

                                String posicions =spinnerConfiguracionS.getSelectedItem().toString();



                                if (posicions.equals("1D-1D")) {

                                    //1D-1D
                                    d1.setVisibility(View.VISIBLE);
                                    d2.setVisibility(View.VISIBLE);
                                    d3.setVisibility(View.VISIBLE);
                                    d4.setVisibility(View.VISIBLE);


                                    f1.setVisibility(View.VISIBLE);
                                    f2.setVisibility(View.VISIBLE);
                                    f3.setVisibility(View.VISIBLE);
                                    f4.setVisibility(View.VISIBLE);

                                    e1.setVisibility(View.INVISIBLE);
                                    e2.setVisibility(View.INVISIBLE);
                                    e3.setVisibility(View.INVISIBLE);
                                    e4.setVisibility(View.INVISIBLE);
                                    //3SS
                                    d11.setVisibility(View.INVISIBLE);
                                    d22.setVisibility(View.INVISIBLE);

                                    e11.setVisibility(View.INVISIBLE);
                                    e22.setVisibility(View.INVISIBLE);

                                    f11.setVisibility(View.INVISIBLE);
                                    f22.setVisibility(View.INVISIBLE);
                                }

                                if (posicions.equals("3D")) {

                                    //1D-1D
                                    d1.setVisibility(View.VISIBLE);
                                    d2.setVisibility(View.VISIBLE);
                                    d3.setVisibility(View.VISIBLE);
                                    d4.setVisibility(View.VISIBLE);

                                    f1.setVisibility(View.VISIBLE);
                                    f2.setVisibility(View.VISIBLE);
                                    f3.setVisibility(View.VISIBLE);
                                    f4.setVisibility(View.VISIBLE);

                                    e1.setVisibility(View.VISIBLE);
                                    e2.setVisibility(View.VISIBLE);
                                    e3.setVisibility(View.VISIBLE);
                                    e4.setVisibility(View.VISIBLE);

                                    //3SS
                                    d11.setVisibility(View.INVISIBLE);
                                    d22.setVisibility(View.INVISIBLE);

                                    e11.setVisibility(View.INVISIBLE);
                                    e22.setVisibility(View.INVISIBLE);

                                    f11.setVisibility(View.INVISIBLE);
                                    f22.setVisibility(View.INVISIBLE);
                                }

                                if (posicions.equals("3SS")) {

                                    //1D-1D
                                    d1.setVisibility(View.INVISIBLE);
                                    d2.setVisibility(View.INVISIBLE);
                                    d3.setVisibility(View.INVISIBLE);
                                    d4.setVisibility(View.INVISIBLE);
                                    f1.setVisibility(View.INVISIBLE);
                                    f2.setVisibility(View.INVISIBLE);
                                    f3.setVisibility(View.INVISIBLE);
                                    f4.setVisibility(View.INVISIBLE);

                                    e1.setVisibility(View.INVISIBLE);
                                    e2.setVisibility(View.INVISIBLE);
                                    e3.setVisibility(View.INVISIBLE);
                                    e4.setVisibility(View.INVISIBLE);

                                    //3SS
                                    d11.setVisibility(View.VISIBLE);
                                    d22.setVisibility(View.VISIBLE);

                                    e11.setVisibility(View.VISIBLE);
                                    e22.setVisibility(View.VISIBLE);

                                    f11.setVisibility(View.VISIBLE);
                                    f22.setVisibility(View.VISIBLE);
                                }


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
                    pDialog.dismiss();
                    //displaying the error in toast if occurrs
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }





            }



    );


    // request queue
    RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest1);


}



    private void addItemToSheet() {




        final ProgressDialog loading = ProgressDialog.show(this, "Cambiando Cubierta", "Por favor Espere");
        final String n_fuego = Fuego_origen.getText().toString().trim();
        final String uso = Uso_origen.getText().toString().trim();
        final String estado = Estado_origen.getText().toString().trim();


        final String patente_origen = Patente_origen.getText().toString().trim();
        final String odo_origen = Odo_origen.getText().toString().trim();
        final String posc_origen = Pos_origen.getText().toString().trim();

        final String p_destino = editTextPatente.getText().toString().trim();
        final String odo_destino = ediTextOdo_destino.getText().toString().trim();
        final String posc_destino = ediTextPosicion.getText().toString().trim();




        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw_75KoOxUf-9hYM-KGWHXnxgo6wmqvvdaupIynTRKt_HQt9rlE/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(Destino.this, response, Toast.LENGTH_LONG).show();
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
                parmas.put("uso", uso);
                parmas.put("estado", estado);

               parmas.put("p_destino", p_destino);
               parmas.put("odo_destino", odo_destino);
               parmas.put("posc_destino", posc_destino);



                parmas.put("p_origen", patente_origen);
                parmas.put("odo_origen", odo_origen);
                parmas.put("posc_origen", posc_origen);


                return parmas;
            }
        };

        int socketTimeOut = 30000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {


        if(v==camion){

            editTextPatente.setVisibility(View.VISIBLE);
            spinnerPatente.setVisibility(View.VISIBLE);
            ediTextOdo_destino.setVisibility(View.VISIBLE);
            ediTextPosicion.setVisibility(View.VISIBLE);

            spinnerSemi.setVisibility(View.INVISIBLE);
            spinnerConfiguracionT.setVisibility(View.INVISIBLE);
            spinnerConfiguracionS.setVisibility(View.INVISIBLE);
            editTextConfigT.setVisibility(View.INVISIBLE);
            editTextConfigS.setVisibility(View.INVISIBLE);


            editTextConfigS.getText().clear();
            editTextPatente.getText().clear();
            ediTextPosicion.getText().clear();
            ediTextOdo_destino.getText().clear();

            d1.setVisibility(View.INVISIBLE);
            d2.setVisibility(View.INVISIBLE);
            d3.setVisibility(View.INVISIBLE);
            d4.setVisibility(View.INVISIBLE);
            f1.setVisibility(View.INVISIBLE);
            f2.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            f4.setVisibility(View.INVISIBLE);
            e1.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            e3.setVisibility(View.INVISIBLE);
            e4.setVisibility(View.INVISIBLE);
            d11.setVisibility(View.INVISIBLE);
            d22.setVisibility(View.INVISIBLE);
            e11.setVisibility(View.INVISIBLE);
            e22.setVisibility(View.INVISIBLE);
            f11.setVisibility(View.INVISIBLE);
            f22.setVisibility(View.INVISIBLE);






        }







        if(v==b_semi){

            spinnerPatente.setVisibility(View.INVISIBLE);
            spinnerConfiguracionT.setVisibility(View.INVISIBLE);
            spinnerSemi.setVisibility(View.VISIBLE);


            editTextPatente.setVisibility(View.VISIBLE);
            ediTextOdo_destino.setVisibility(View.VISIBLE);
            ediTextPosicion.setVisibility(View.VISIBLE);
            spinnerPatente.bringToFront();

            spinnerConfiguracionT.setVisibility(View.INVISIBLE);
            spinnerConfiguracionS.setVisibility(View.INVISIBLE);


                editTextConfigS.setVisibility(View.INVISIBLE);
            editTextConfigT.setVisibility(View.INVISIBLE);


            editTextConfigT.getText().clear();
            editTextPatente.getText().clear();
            ediTextPosicion.getText().clear();
            ediTextOdo_destino.getText().clear();




            a1.setVisibility(View.INVISIBLE);
            a2.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
            c11.setVisibility(View.INVISIBLE);
            c22.setVisibility(View.INVISIBLE);




        }



        if(v==enviar) {

            if (
                                (Odo_origen.getText().toString().trim().length() != 0)
                            && (Fuego_origen.getText().toString().trim().length() != 0)
                            && (Patente_origen.getText().toString().trim().length() != 0) && (Uso_origen.getText().toString().trim().length() != 0)
                            && (Estado_origen.getText().toString().trim().length() != 0)
                            && (Pos_origen.getText().toString().trim().length() != 0)
                                        && (ediTextPosicion.getText().toString().trim().length() != 0)
                                        && (ediTextOdo_destino.getText().toString().trim().length()  != 0)
                                        &&(editTextPatente.getText().toString().trim().length() != 0)
  ) {

                addItemToSheet();
            } else {

                Toast.makeText(Destino.this, "Ningun Campo puede quedar vacio", Toast.LENGTH_LONG).show(); //Correcto

            }
        }



        if (v == a1) {
            ediTextPosicion.setText("A1");


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
        if(v==d1) {
            ediTextPosicion.setText("D1");
        }
        if(v==d2) {
            ediTextPosicion.setText("D2");
        }
        if(v==d3) {
            ediTextPosicion.setText("D3");
        }
        if(v==d4) {
            ediTextPosicion.setText("D4");
        }
        if(v==d11) {
            ediTextPosicion.setText("D11");
        }
        if(v==d22) {
            ediTextPosicion.setText("D22");
        }
        if(v==e1) {
            ediTextPosicion.setText("E1");
        }
        if(v==e2) {
            ediTextPosicion.setText("E2");
        }
        if(v==e3) {
            ediTextPosicion.setText("E3");
        }
        if(v==e4) {
            ediTextPosicion.setText("E4");
        }
        if(v==e11) {
            ediTextPosicion.setText("E11");
        }
        if(v==e22) {
            ediTextPosicion.setText("E22");
        }if(v==f1) {
            ediTextPosicion.setText("F1");
        }
        if(v==f2) {
            ediTextPosicion.setText("F2");
        }
        if(v==f3) {
            ediTextPosicion.setText("F3");
        }
        if(v==f4) {
            ediTextPosicion.setText("F4");
        }
        if(v==f11) {
            ediTextPosicion.setText("F11");
        }
        if(v==f22) {
            ediTextPosicion.setText("F22");
        }



        if(v==gomeria){

            spinnerPatente.setVisibility(View.INVISIBLE);
            spinnerConfiguracionT.setVisibility(View.INVISIBLE);
            spinnerSemi.setVisibility(View.INVISIBLE);


            editTextPatente.setVisibility(View.VISIBLE);
            ediTextOdo_destino.setVisibility(View.VISIBLE);
            ediTextPosicion.setVisibility(View.VISIBLE);
            //spinnerPatente.bringToFront();

            spinnerConfiguracionT.setVisibility(View.INVISIBLE);
            spinnerConfiguracionS.setVisibility(View.INVISIBLE);


            editTextConfigS.setVisibility(View.INVISIBLE);
            editTextConfigT.setVisibility(View.INVISIBLE);


            editTextConfigT.getText().clear();
            editTextPatente.getText().clear();
            ediTextPosicion.getText().clear();
            ediTextOdo_destino.getText().clear();


            editTextPatente.setText("GOMERIA");
            ediTextOdo_destino.setText("Gomeria");
            ediTextPosicion.setText("GOMERIA");





            a1.setVisibility(View.INVISIBLE);
            a2.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
            c11.setVisibility(View.INVISIBLE);
            c22.setVisibility(View.INVISIBLE);
            d1.setVisibility(View.INVISIBLE);
            d2.setVisibility(View.INVISIBLE);
            d3.setVisibility(View.INVISIBLE);
            d4.setVisibility(View.INVISIBLE);
            f1.setVisibility(View.INVISIBLE);
            f2.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            f4.setVisibility(View.INVISIBLE);
            e1.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            e3.setVisibility(View.INVISIBLE);
            e4.setVisibility(View.INVISIBLE);
            d11.setVisibility(View.INVISIBLE);
            d22.setVisibility(View.INVISIBLE);
            e11.setVisibility(View.INVISIBLE);
            e22.setVisibility(View.INVISIBLE);
            f11.setVisibility(View.INVISIBLE);
            f22.setVisibility(View.INVISIBLE);




        }





    }


}