package com.alumn.InventarySystem.Modelo;

/**
 * Created by CedenoSalazarBryanCa on 28/12/2016.
 */

public class modelo_imagenes {
    private String name;
    private String numOfSongs;
    private int thumbnail;

    public modelo_imagenes() {
    }

    public modelo_imagenes(String name, String numOfSongs, int thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(String numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
