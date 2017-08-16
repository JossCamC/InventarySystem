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

public class controlador_venta extends conexion_base_de_datos {
    EditText descri;
    TextView idven, descriV, cantidadV, precioVent, fechaV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_ventas);

        descri = (EditText)findViewById(R.id.txtDescripC);

        idven= (TextView)findViewById(R.id.txtIdVen);
        descriV = (TextView)findViewById(R.id.txtDescriVent);
        cantidadV= (TextView)findViewById(R.id.txtCantVen);
        precioVent = (TextView)findViewById(R.id.txtPrecioVent);
        fechaV = (TextView)findViewById(R.id.txtFechaVen);



    }
    public void consultarRegistro (View view){

        String strUserName = descri.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            descri.setError("Error campo vacío");
            return;
        }else {
            new ConsultarDatos().execute("https://inventarysystems.000webhostapp.com/consulta_venta.php?fecha_venta="+descri.getText().toString());
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
                idven.setText(ja.getString(0));
                descriV.setText(ja.getString(1));
                cantidadV.setText(ja.getString(2));
                precioVent.setText(ja.getString(3));
                fechaV.setText(ja.getString(4));


            } catch (JSONException e) {
                dialog.cancel();

                Toast.makeText(getApplicationContext(), "No existe el producto", Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(controlador_venta.this, AlertDialog.THEME_HOLO_LIGHT);
            dialog.setMessage("Espere un momento por favor...");
            dialog.setTitle("Verificando producto");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.show();
        }


    }

}