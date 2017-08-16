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

import com.alumn.InventarySystem.*;
import com.alumn.InventarySystem.DAO.*;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class controlador_producto extends conexion_base_de_datos {
    EditText descri;
    TextView prevee, descrip, existen, precioC, preciov, preciou, stock, cater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_productos);

        descri = (EditText)findViewById(R.id.txtDescripC);
        prevee = (TextView)findViewById(R.id.txtProveedor);
        descrip= (TextView)findViewById(R.id.txtDescri);
        existen = (TextView)findViewById(R.id.txtExist);
        precioC= (TextView)findViewById(R.id.txtPrecioC);
        preciov = (TextView)findViewById(R.id.txtPrecioV);
        preciou = (TextView)findViewById(R.id.txtPrecioU);
        stock = (TextView)findViewById(R.id.txtStock);


    }
    public void consultarRegistro (View view){

        String strUserName = descri.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            descri.setError("Error campo vacío");
            return;
        }else {
            new ConsultarDatos().execute("https://inventarysystems.000webhostapp.com/consulta.php?descripcion_producto="+descri.getText().toString());
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

                prevee.setText(ja.getString(2));
                descrip.setText(ja.getString(3));
                existen.setText(ja.getString(4));
                precioC.setText(ja.getString(5));
                preciov.setText(ja.getString(6));
                preciou.setText(ja.getString(7));
                stock.setText(ja.getString(8));

            } catch (JSONException e) {
                dialog.cancel();

                Toast.makeText(getApplicationContext(), "No existe el producto", Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(controlador_producto.this, AlertDialog.THEME_HOLO_LIGHT);
            dialog.setMessage("Espere un momento por favor...");
            dialog.setTitle("Verificando producto");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.show();
        }


    }

}