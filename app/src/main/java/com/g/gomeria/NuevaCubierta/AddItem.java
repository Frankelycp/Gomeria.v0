package com.g.gomeria.NuevaCubierta;

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
import com.g.gomeria.CambioCubierta.Gomeria;
import com.g.gomeria.Clases.Marca;
import com.g.gomeria.Clases.Medida;
import com.g.gomeria.Clases.Tipo;
import com.g.gomeria.MainActivity;
import com.g.gomeria.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;







import static android.R.layout.simple_spinner_item;


public class AddItem extends AppCompatActivity  {

    private ProgressDialog pDialog;


    private String URLstring = "https://script.googleusercontent.com/a/macros/transchemicalsa.com.ar/echo?user_content_key=g2QYajePlcCa9d_YJKEc5TtaE_lPGy1AIdGBiVa2BIZfv0IFLoqWFb30c6ZIW1zHpd_-kXkNCb4ilPMKjPhCUdZQ6f64d5bBOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKDI_4Q-pKkLe7MNxJdrWyCsJV3ddGLkNmSAGz9x4Gmo7upEGACIRS2gvVvOc8yrT9pLX6HqBmn5ueosw75brm8XOxguoQHxo4t911Uq9f875pC0PWKWrtrCndNuyBbD-5uwbiTIP26maNIeZ2OIybTOY7sOArVtvnmvObqsO96hwSMyFZdnCJNOo-s-rPDCSt8JuS3Mld3bLg&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

    private ArrayList<Marca> MarcaArrayList;
    private ArrayList<Tipo> TipoArrayList;
    private ArrayList<Medida> MedidaArrayList;

    private ArrayList<Medida> medidaArrayList;
    private ArrayList<Tipo> tipoArrayList;
    private ArrayList<Marca> marcaArrayList;


    private ArrayList<String> tipo = new ArrayList<>();
    private ArrayList<String> marca = new ArrayList<>();
    private ArrayList<String> medida = new ArrayList<>();


    private Spinner spinnerMarca;
    private Spinner spinnerTipo;
    private Spinner spinnerMedida;


    EditText editTextItemFuego, editTextMedida, editTextMarca, editTextTipo, editTextCantidad;
    Button buttonAddItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerMedida = findViewById(R.id.spinnerMedida);


        editTextItemFuego = findViewById(R.id.et_item_fuego);
        editTextMedida = findViewById(R.id.et_medida);
        editTextMarca = findViewById(R.id.et_marca);
        editTextTipo = findViewById(R.id.et_tipo);
        editTextCantidad = findViewById(R.id.et_cantidad);


        buttonAddItem = findViewById(R.id.btn_add_item);
        displayLoader();

        retrieveJSON();



        buttonAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((editTextItemFuego.getText().toString().trim().length() != 0) && (editTextMedida.getText().toString().trim().length() != 0) && (editTextTipo.getText().toString().trim().length() != 0) && (editTextMarca.getText().toString().trim().length() != 0) && (editTextCantidad.getText().toString().trim().length() != 0)) {

                    addItemToSheet();



                } else {

                    editTextItemFuego.setError("Ingrese Numero de Fuego!");
                    editTextMedida.setError("Ingrese Medida!");
                    editTextTipo.setError("Ingrese Tipo!");
                    editTextCantidad.setError("Ingrese Cantidad!");
                    editTextMarca.setError("Ingrese Marca!");
                    Toast.makeText(AddItem.this, "No puede quedar campos vacios!", Toast.LENGTH_LONG).show();

                }
            }

    });

    }

    private void displayLoader(){
        pDialog = new ProgressDialog(AddItem.this);
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
                            marcaArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Marca playerModel = new Marca();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerModel.setMarca(dataobj.getString("MARCA"));
                                marcaArrayList.add(playerModel);
                            }

                            for (int i = 0; i < marcaArrayList.size(); i++) {
                                if (i == 0) {
                                    marca.add(marcaArrayList.get(i).getMarca());
                                }
                                else if ((!marcaArrayList.get(i).getMarca().equals("")) && (i != 0)) {
                                    marca.add(marcaArrayList.get(i).getMarca());
                                }
                            }


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddItem.this, simple_spinner_item, marca);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerMarca.setAdapter(spinnerArrayAdapter);
                            spinnerMarca.setSelection(spinnerArrayAdapter.NO_SELECTION, false);


                            spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    editTextMarca.setText((CharSequence) spinnerMarca.getSelectedItem());
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
                            tipoArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Tipo playerMode = new Tipo();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode.setTipo(dataobj.getString("TIPO"));
                                tipoArrayList.add(playerMode);
                            }


                            for (int i = 0; i < tipoArrayList.size(); i++) {
                                if (i == 0) {
                                    tipo.add(tipoArrayList.get(i).getTipo());
                                }
                                else if ((!tipoArrayList.get(i).getTipo().equals("")) && (i != 0)) {
                                    tipo.add(tipoArrayList.get(i).getTipo());
                                }
                            }



                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddItem.this, simple_spinner_item, tipo);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerTipo.setAdapter(spinnerArrayAdapter);
                            spinnerTipo.setSelection(spinnerArrayAdapter.NO_SELECTION, false);


                            spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    editTextTipo.setText((CharSequence) spinnerTipo.getSelectedItem());
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
                            medidaArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("datos");
                            for (int i = 0; i < dataArray.length(); i++) {
                                Medida playerMode2 = new Medida();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                playerMode2.setMedida(dataobj.getString("MEDIDA"));
                                medidaArrayList.add(playerMode2);
                            }


                            for (int i = 0; i < medidaArrayList.size(); i++) {
                                if (i == 0) {
                                    medida.add(medidaArrayList.get(i).getMedida());
                                }
                                else if ((!medidaArrayList.get(i).getMedida().equals("")) && (i != 0)) {
                                    medida.add(medidaArrayList.get(i).getMedida());
                                }
                            }


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddItem.this, simple_spinner_item, medida);


                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerMedida.setAdapter(spinnerArrayAdapter);
                            spinnerMedida.setSelection(spinnerArrayAdapter.NO_SELECTION, false);


                            spinnerMedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                                    editTextMedida.setText((CharSequence) spinnerMedida.getSelectedItem());
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


    //A PARTIR DE ACA ES PARA LLENAR LOS DATOS


    private void addItemToSheet() {


        final ProgressDialog loading = ProgressDialog.show(this, "Guardado Cubierta", "Por favor Espere");
        final String n_fuego = editTextItemFuego.getText().toString().trim();
        final String marca = editTextMarca.getText().toString().trim();
        final String medida = editTextMedida.getText().toString().trim();
        final String tipo = editTextTipo.getText().toString().trim();
        final String cantidad = editTextCantidad.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzyPYaSO_kdZLU4pa6KdLmjhS69LmEYl5M51Etn1vOfxakJDFA/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(AddItem.this, response, Toast.LENGTH_LONG).show();
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
                parmas.put("medida", medida);
                parmas.put("tipo", tipo);
                parmas.put("marca", marca);
                parmas.put("cantidad", cantidad);


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
