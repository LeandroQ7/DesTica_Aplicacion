package com.example.desticaaplicacion.ui.intereses;

public class Destiny {
    private int iddestination;
    private float distanciacalculada;
    private String title, image;

    public void setIddestino(int id) {  iddestination = id;  }
    public void setDistanciacalculada(float dist) {  distanciacalculada = dist;  }
    public void setTitle(String tl) {  title = tl;  }
    public void setImage(String img) {  image = img;  }

    public int getIddestino() { return this.iddestination; }
    public String getImage() { return this.image; }

}
