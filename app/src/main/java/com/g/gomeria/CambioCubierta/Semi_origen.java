package com.g.gomeria.CambioCubierta;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.g.gomeria.Clases.ConfS;

import com.g.gomeria.Clases.Uso;
import com.g.gomeria.Clases.Estado;

import com.g.gomeria.Clases.Semi;
import com.g.gomeria.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import static android.R.layout.simple_spinner_item;


public class Semi_origen extends AppCompatActivity implements View.OnClickListener {


    private ProgressDialog pDialog;




    private String URLstring = "https://script.googleusercontent.com/a/macros/transchemicalsa.com.ar/echo?user_content_key=g2QYajePlcCa9d_YJKEc5TtaE_lPGy1AIdGBiVa2BIZfv0IFLoqWFb30c6ZIW1zHpd_-kXkNCb4ilPMKjPhCUdZQ6f64d5bBOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDI_4Q-pKkLe7MNxJdrWyCsJV3ddGLkNmSAGz9x4Gmo7upEGACIRS2gvVvOc8yrT9pLX6HqBmn5ueosw75brm8XOxguoQHxo4t911Uq9f875pC0PWKWrtrCndNuyBbD-5uwbiTIP26maNIeZ2OIybTOY7sOArVtvnmvObqsO96hwSMyFZdnCJNOo-s-rPDCSt8JuS3Mld3bLg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";


    private ArrayList<Semi> SemiArrayList;
    private ArrayList<Uso> UsoArrayList;
    private ArrayList<Estado> EstadoArrayList;

    private ArrayList<ConfS> ConfiSArrayList;





    //SE DECLARAN LAS LISTAS DE LOS ARRAY PARA LOS ADAPTERS
    private ArrayList<Semi> semiArrayList;
    private ArrayList<Uso> usoArrayList;
    private ArrayList<Estado> estadoArrayList;
    private ArrayList<ConfS> confSArrayList;



//SE DECLARAN LAS LISTAS PARA LOS ARRAY

    private ArrayList<String> semi = new ArrayList<>();
    private ArrayList<String> estado = new ArrayList<>();
    private ArrayList<String> uso = new ArrayList<>();
    private ArrayList<String> confS = new ArrayList<>();



// SE DECLARAN LOS EDITVIEW

    EditText editTextPatente;
    EditText editTextfuego;
    EditText ediTextOdo_destino;
    EditText editTextUso;
    EditText ediTextEstado;
    EditText ediTextPosicion;
    EditText editTextConfigS;





    //SE DECLARAN LOS SPINNER CAMION
    private Spinner spinnerUso;
    private Spinner spinnerEstado;
    private Spinner spinnerSemi;
    private Spinner spinnerConfiguracionS;

    //SE DECLARAN LOS BOTONES

    Button enviar;


    // SE DECLARAN  LOS BOTONES DE LAS RUEDAS

    Integer PosicionSemi ;



    ImageButton d11,d22;
    ImageButton e11,e22;
    ImageButton f11,f22;

    ImageButton d1,d2,d3,d4;
    ImageButton e1,e2,e3,e4;
    ImageButton f1,f2,f3,f4;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.origen_semi);



//LE INDICO A LOS SPINER DE DONDE TOMAR EL VALOR EN EL XML
        spinnerSemi = findViewById(R.id.spinnerSemi);
        spinnerUso = findViewById(R.id.spinnerUso);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        spinnerConfiguracionS = findViewById(R.id.spinnerConfigSemi);



//BOTONES PARA SELECIONAR A QUIEN SE LE CAMBIARA




        enviar = (Button)findViewById(R.id.enviar) ;
        enviar.setOnClickListener(this);


        ediTextPosicion = (EditText)findViewById(R.id.posicionCamion);
        ediTextPosicion.setOnClickListener(this);





        // lAS CUBIERTAS DEL SEMI


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





// CASILLAS DE TEXTO PARA LOS SPINER
        editTextPatente = findViewById(R.id.patente);
        editTextfuego = findViewById(R.id.n_fuego);
        ediTextOdo_destino = findViewById(R.id.n_odo);
        editTextUso = findViewById(R.id.usoPatente);
        ediTextEstado = findViewById(R.id.estado);
        ediTextPosicion = findViewById(R.id.posicionCamion);
        editTextConfigS = findViewById(R.id.configS);





// esconder spinner de seleccion
        spinnerSemi.setVisibility(View.VISIBLE);






        editTextConfigS.getText().clear();
        editTextPatente.getText().clear();
        editTextUso.getText().clear();
        ediTextEstado.getText().clear();
        ediTextPosicion.getText().clear();
        ediTextOdo_destino.getText().clear();
        editTextfuego.getText().clear();
        editTextConfigS.getText().clear();




//ocultar Para cambio a camiones
        editTextPatente.setVisibility(View.VISIBLE);
        editTextfuego.setVisibility(View.VISIBLE);
        ediTextOdo_destino.setVisibility(View.VISIBLE);
        editTextUso.setVisibility(View.VISIBLE);
        ediTextEstado.setVisibility(View.VISIBLE);
        ediTextPosicion.setVisibility(View.VISIBLE);
        spinnerUso.setVisibility(View.VISIBLE);
        spinnerEstado.setVisibility(View.VISIBLE);
        editTextConfigS.setVisibility(View.VISIBLE);
        spinnerConfiguracionS.setVisibility(View.VISIBLE);




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



        editTextConfigS.setFocusable(false);
        editTextConfigS.setKeyListener(null);



        ediTextEstado.setFocusable(false);
        ediTextEstado.setKeyListener(null);
        editTextUso.setFocusable(false);
        editTextUso.setKeyListener(null);
        editTextPatente.setFocusable(false);
        editTextPatente.setKeyListener(null);


//OBTENGO EL JSON
        displayLoader();
        retrieveJSON();

    }


    private void displayLoader(){
        pDialog = new ProgressDialog(Semi_origen.this);
        pDialog.setMessage("Cargando Datos.. Espere un momento...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }


//CLASE PARA EL JSON DONDE INVOCA LOS DATOS DE GOOGLE



    private void retrieveJSON() {


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("strrrrr", ">>" + response);





// INTENTA BUSCAR LOS DATOS SELECCIONADOS EN EL JSON POR EL NOMBRE DE LA HOJA Y POR EL NOMBRE DE LA FILA  LOS GUARDA EN LOS ARRAY DEL ADAPTER Y EL ARRAY COMUN

                        // PATENTE




                        //CONFIGURACION S


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


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Semi_origen.this, simple_spinner_item, confS);
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


                        //USO


                        try {
                            JSONObject obj = new JSONObject(response);
                            usoArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Uso playerMode2 = new Uso ();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode2.setUso(dataobj.getString("USO"));
                                usoArrayList.add(playerMode2);
                            }
                            for (int i = 0; i < usoArrayList.size(); i++) {
                                if (i == 0){
                                    uso.add(usoArrayList.get(i).getUso());
                                }
                                else if ((!usoArrayList.get(i).getUso().equals("")) && (i != 0)) {
                                    uso.add(usoArrayList.get(i).getUso());
                                }
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Semi_origen.this, simple_spinner_item, uso);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerUso.setAdapter(spinnerArrayAdapter);
                            spinnerUso.setSelection(spinnerArrayAdapter.NO_SELECTION, false);



                            spinnerUso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    editTextUso.setText((CharSequence) spinnerUso.getSelectedItem());
                                    ((TextView) view).setText(null);



                                }
                                public void onNothingSelected(AdapterView<?> parent) {
                                }

                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //SEMI

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

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Semi_origen.this, simple_spinner_item, semi);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerSemi.setAdapter(spinnerArrayAdapter);
                            spinnerSemi.setSelection(spinnerArrayAdapter.NO_SELECTION, false);





                            spinnerSemi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int posi, long id) {

                                    PosicionSemi = posi;
                                    spinnerConfiguracionS.setSelection(PosicionSemi);


                                    editTextPatente.setText((CharSequence) spinnerSemi.getSelectedItem());
                                    ((TextView) view).setText(null);

                                    String posicions = spinnerConfiguracionS.getSelectedItem().toString();


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


                        // ESTADO


                        try {
                            JSONObject obj = new JSONObject(response);
                            estadoArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Estado playerMode3 = new Estado();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode3.setEstado(dataobj.getString("ESTADO"));
                                estadoArrayList.add(playerMode3);
                            }


                            for (int i = 0; i < estadoArrayList.size(); i++) {
                                if (i == 0) {
                                    estado.add(estadoArrayList.get(i).getEstado());
                                }
                                if ((!estadoArrayList.get(i).getEstado().equals("")) && (i != 0)) {
                                    estado.add(estadoArrayList.get(i).getEstado());
                                }
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Semi_origen.this, simple_spinner_item, estado);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerEstado.setAdapter(spinnerArrayAdapter);

                            spinnerEstado.setSelection(spinnerArrayAdapter.NO_SELECTION, false);



                            spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    ediTextEstado.setText((CharSequence) spinnerEstado.getSelectedItem());
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





    // A PARTIR DE ACA ES PARA LLENAR LOS DATOS


    @Override
    public void onClick(View v) {


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






        if (v==ediTextPosicion){


        }





        if(v==enviar) {

            if ((editTextConfigS.getText().toString().trim().length() != 0)
                    &&(ediTextOdo_destino.getText().toString().trim().length() != 0)
                    && (editTextfuego.getText().toString().trim().length() != 0)
                    && (editTextPatente.getText().toString().trim().length() != 0)&&(editTextUso.getText().toString().trim().length() != 0)
                    &&(ediTextEstado.getText().toString().trim().length() != 0)
                    &&(ediTextPosicion.getText().toString().trim().length() != 0)) {

                String fuego = editTextfuego.getText().toString();
                String uso = editTextUso.getText().toString();
                String estado = ediTextEstado.getText().toString();
                String patente = editTextPatente.getText().toString();
                String odo = ediTextOdo_destino.getText().toString();
                String posicion = ediTextPosicion.getText().toString();




                Intent i = new Intent(Semi_origen.this, Destino.class);
                i.putExtra("fuego", fuego);
                i.putExtra("uso", uso);
                i.putExtra("estado", estado);
                i.putExtra("patente", patente);
                i.putExtra("odo", odo);
                i.putExtra("posicion", posicion);


                startActivity(i);


            } else {

                Toast.makeText(Semi_origen.this, "Ningun Campo puede quedar vacio", Toast.LENGTH_LONG).show(); //Correcto

            }




        }

    }




}