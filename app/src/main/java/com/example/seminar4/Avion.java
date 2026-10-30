package com.example.seminar4;

import java.io.Serializable;

public class Avion implements Serializable {
    private String companie;
    private String model;
    private String tip;
    private String clasa;
    private boolean wifi;
    private boolean international;
    private float rating;

    public Avion(String companie, String model, String tip, String clasa,
                 boolean wifi, boolean international, float rating) {
        this.companie = companie;
        this.model = model;
        this.tip = tip;
        this.clasa = clasa;
        this.wifi = wifi;
        this.international = international;
        this.rating = rating;
    }

    public String getCompanie() { return companie; }
    public void setCompanie(String companie) { this.companie = companie; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public String getClasa() { return clasa; }
    public void setClasa(String clasa) { this.clasa = clasa; }

    public boolean isWifi() { return wifi; }
    public void setWifi(boolean wifi) { this.wifi = wifi; }

    public boolean isInternational() { return international; }
    public void setInternational(boolean international) { this.international = international; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    @Override
    public String toString() {
        return companie + " - " + model +
                "\nTip: " + tip +
                ", Clasa: " + clasa +
                "\nWiFi: " + (wifi ? "da" : "nu") +
                ", International: " + (international ? "da" : "nu") +
                ", Rating: " + rating;
    }
}
