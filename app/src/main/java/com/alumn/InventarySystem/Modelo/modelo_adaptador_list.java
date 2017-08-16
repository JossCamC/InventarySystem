package com.alumn.InventarySystem.Modelo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumn.InventarySystem.Modelo.modelo_list;
import com.alumn.InventarySystem.R;

/**
 * Created by CedenoSalazarBryanCa on 4/12/2016.
 */

public class modelo_adaptador_list extends ArrayAdapter<modelo_list>{
    Context mycontext;
    int myLayautResour;
    modelo_list mydata[]=null;

    public modelo_adaptador_list(Context context, int layotresourceid, modelo_list[] data) {
        super(context, layotresourceid, data);
        this.mycontext=context;
        this.myLayautResour=layotresourceid;
        this.mydata=data;

    }
    public View getView(int posicion, View contenvita, ViewGroup parent){
        View listaAFT=contenvita;
        ListaAcFTHolder holder=null;
        if(listaAFT==null){
            LayoutInflater inflater=((Activity)mycontext).getLayoutInflater();
            listaAFT = inflater.inflate(myLayautResour, parent,false);
            holder=new ListaAcFTHolder();
            holder.imagen=(ImageView)listaAFT.findViewById(R.id.dolor);

            holder.textView=((TextView)listaAFT.findViewById(R.id.txtdolor));

            listaAFT.setTag(holder);

        }else{
            holder=((ListaAcFTHolder)listaAFT.getTag());
        }

        modelo_list modelo_list = mydata [posicion];
        holder.textView.setText(modelo_list.title);
        holder.imagen.setImageResource(modelo_list.inco);


        return listaAFT;
    }
    static class ListaAcFTHolder{
        ImageView imagen;
        TextView textView;


    }
}
