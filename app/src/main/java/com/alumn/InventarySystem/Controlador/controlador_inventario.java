package com.alumn.InventarySystem.Controlador;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alumn.InventarySystem.DAO.conexion_base_de_datos;
import com.alumn.InventarySystem.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class controlador_inventario extends conexion_base_de_datos {
    EditText descri;
    TextView idin, descripI, producI, piezaE, piezaS, total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_inventario);

        descri = (EditText)findViewById(R.id.txtDescripC);
        idin= (TextView)findViewById(R.id.txtIdI);
        descripI = (TextView)findViewById(R.id.txtDescriI);
        producI= (TextView)findViewById(R.id.txtProduI);
        piezaE = (TextView)findViewById(R.id.txtPiezaE);
        piezaS= (TextView)findViewById(R.id.txtPiezaS);
        total= (TextView)findViewById(R.id.txtRestantes);



    }
    public void consultarRegistro (View view){

        String strUserName = descri.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            descri.setError("Error campo vacío");
            return;
        }else {
            new ConsultarDatos().execute("https://inventarysystems.000webhostapp.com/consulta_inventario.php?id_inventario="+descri.getText().toString());
        }
    }
    public class ConsultarDatos extends AsyncTask<String, Void, String> {
        public ProgressDialog dialog;
        Context context;
        @Override
        public String doInBackground(String... urls) {

            String url = urls[0];
            try {
                return downloadUrl(urls[0]);

            } catch (IOException e){
                e.printStackTrace();
            }
            return url;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        public void onPostExecute(String result) {


            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                dialog.cancel();
                idin.setText(ja.getString(0));
                descripI.setText(ja.getString(1));
                producI.setText(ja.getString(2));
                piezaE.setText(ja.getString(3));
                piezaS.setText(ja.getString(4));
                total.setText(ja.getString(5));


            } catch (JSONException e) {
                dialog.cancel();

                Toast.makeText(getApplicationContext(), "No existe el producto", Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(controlador_inventario.this, AlertDialog.THEME_HOLO_LIGHT);
            dialog.setMessage("Espere un momento por favor...");
            dialog.setTitle("Verificando producto");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.show();
        }


    }

}