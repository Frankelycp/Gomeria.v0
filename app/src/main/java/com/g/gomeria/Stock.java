package com.g.gomeria;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.tutorialspoint7.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;





public class Stock extends AppCompatActivity {




    private String TAG = Stock.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView list;


    // URL to get Sheet1 JSON
    private static String url = "https://script.googleusercontent.com/macros/echo?user_content_key=5eiFfPWuMnF-iSJ46xc7eVXnzJ5x9tyWhUvtjGNeTUueW5QcYPcX8efTsQwgwM59-d5eaEpit9YBfTD4TVuFFvleEohnrfWTOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUDFLHTWrhB1wewdLro56xPVSWbDL2OlNarWDIGK14Cd465L2Ug6EwLgz7NxCqgIKm5y7FLqOV0TnXnPf7ksCyxQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock);
        contactList = new ArrayList<>();
        list = (ListView) findViewById(R.id.list);

        new GetSheet1().execute();

    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetSheet1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Stock.this);
            pDialog.setMessage("Por favor espere...");
            pDialog.setCancelable(false);
            pDialog.show();


        }


        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                    try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray Sheet1 = jsonObj.getJSONArray("Stock");

                    // looping through All Sheet1
                    for (int i = 0; i < Sheet1.length(); i++) {
                        JSONObject c = Sheet1.getJSONObject(i);

                        String MARCA = c.getString("MARCA");
                        String TAMAÑO295 = c.getString("TAMAÑO295");
                        String TAMAÑO385 = c.getString("TAMAÑO385");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("MARCA", MARCA);
                        contact.put("TAMAÑO295", TAMAÑO295);
                        contact.put("TAMAÑO385", TAMAÑO385);



                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }

                    });

                }
            } else {
                Log.e(TAG, "No se puede conectar con el servidor");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "No se puede cconectar con el servidor",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
            ACTUALIZA LOS DATOS DE LA GOOGLE SHEETS             * */

            ListAdapter adapter = new SimpleAdapter(
                    Stock.this, contactList,
                    R.layout.list_item, new String[]{"MARCA", "TAMAÑO295", "TAMAÑO385"}, new int[]{R.id.marca,
                    R.id.tamaño ,R.id.tamaño2});

            list.setAdapter(adapter);


        }

    }

}







