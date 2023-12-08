package com.example.endevinaelnumero;

public class Record {
    private String nom;
    private int puntuacio;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public Record(String nom, int puntuacio) {
        this.nom = nom;
        this.puntuacio = puntuacio;
    }
}
