package com.example.cleve.mutantesws;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.List;

public class Mutante {
    private long id;
    private String nome;
    private List<String> poderes;
    private Bitmap foto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getPoderes() {
        return poderes;
    }

    public void setPoderes(List<String> poderes) {
        this.poderes = poderes;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(String imgByte) {
        if(!imgByte.isEmpty()) {
           byte[] img = Base64.decode(imgByte, Base64.DEFAULT);
           this.foto = BitmapFactory.decodeByteArray(img, 0, img.length);
        }
    }
}

