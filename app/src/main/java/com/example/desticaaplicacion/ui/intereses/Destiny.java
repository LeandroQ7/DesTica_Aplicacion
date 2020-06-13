package com.example.desticaaplicacion.ui.intereses;

public class Destiny {
    private int iddestination;
    private double distanciacalculada;
    private String title, image;

    Destiny(int id, double distancia, String tl, String img){
        iddestination=id;
        distanciacalculada=distancia;
        title=tl;
        image=img;
    }

    public void setIddestino(int id) {  iddestination = id;  }
    public void setDistanciacalculada(double dist) {  distanciacalculada = dist;  }
    public void setTitle(String tl) {  title = tl;  }
    public void setImage(String img) {  image = img;  }

    public int getIddestino() { return this.iddestination; }
    public String getImage() { return this.image; }
    public String getTitle() { return this.title; }
    public Double getDistanciacalculada() { return this.distanciacalculada; }

    @Override
    public String toString(){
        return String.format("%s: %s %s %s", iddestination, distanciacalculada, title, image);
    }
}
