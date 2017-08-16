package com.alumn.InventarySystem.Modelo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumn.InventarySystem.Controlador.*;
import com.alumn.InventarySystem.R;
import com.bumptech.glide.Glide;
import java.util.List;


/**
 * Created by CedenoSalazarBryanCa on 28/12/2016.
 */

public class modelo_imagenesadapter extends RecyclerView.Adapter<modelo_imagenesadapter.MyViewHolder>  {
    private Context mContext;
    private List<modelo_imagenes> albumList;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public Context context;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position==0){
                        Intent llamaJuego = new Intent(context, controlador_producto.class);
                        context.startActivity(llamaJuego);
                    }
                    if (position==1){
                        Intent llamaJuego = new Intent(context, controlador_proveedor.class);
                        context.startActivity(llamaJuego);
                    }
                    if (position==2){
                        Intent llamaJuego = new Intent(context, controlador_venta.class);
                        context.startActivity(llamaJuego);
                    }
                    if (position==3){
                        Intent llamaJuego = new Intent(context, controlador_inventario.class);
                        context.startActivity(llamaJuego);
                    }
                }
            });
        }
        void setOnClickListener(){
            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public modelo_imagenesadapter(Context mContext, List<modelo_imagenes> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vista_menu_imagenes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        modelo_imagenes album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + "");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        holder.setOnClickListener();


    }


    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_imagenes, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_play_next:

                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
