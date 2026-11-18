package com.example.laborator8;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Avion implements Parcelable {

    private String model;
    private String producator;
    private int nrLocuri;
    private String tip;
    private boolean areWifi;
    private boolean inServiciu;
    private float rating;

    public Avion(String model, String producator, int nrLocuri, String tip,
                 boolean areWifi, boolean inServiciu, float rating) {
        this.model = model;
        this.producator = producator;
        this.nrLocuri = nrLocuri;
        this.tip = tip;
        this.areWifi = areWifi;
        this.inServiciu = inServiciu;
        this.rating = rating;
    }

    protected Avion(Parcel in) {
        model = in.readString();
        producator = in.readString();
        nrLocuri = in.readInt();
        tip = in.readString();
        areWifi = in.readByte() != 0;
        inServiciu = in.readByte() != 0;
        rating = in.readFloat();
    }

    public static final Creator<Avion> CREATOR = new Creator<Avion>() {
        @Override
        public Avion createFromParcel(Parcel in) {
            return new Avion(in);
        }

        @Override
        public Avion[] newArray(int size) {
            return new Avion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(model);
        dest.writeString(producator);
        dest.writeInt(nrLocuri);
        dest.writeString(tip);
        dest.writeByte((byte) (areWifi ? 1 : 0));
        dest.writeByte((byte) (inServiciu ? 1 : 0));
        dest.writeFloat(rating);
    }


    public void actualizeazaDin(Avion altul) {
        this.model = altul.model;
        this.producator = altul.producator;
        this.nrLocuri = altul.nrLocuri;
        this.tip = altul.tip;
        this.areWifi = altul.areWifi;
        this.inServiciu = altul.inServiciu;
        this.rating = altul.rating;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getProducator() { return producator; }
    public void setProducator(String producator) { this.producator = producator; }

    public int getNrLocuri() { return nrLocuri; }
    public void setNrLocuri(int nrLocuri) { this.nrLocuri = nrLocuri; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public boolean isAreWifi() { return areWifi; }
    public void setAreWifi(boolean areWifi) { this.areWifi = areWifi; }

    public boolean isInServiciu() { return inServiciu; }
    public void setInServiciu(boolean inServiciu) { this.inServiciu = inServiciu; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    @NonNull
    @Override
    public String toString() {
        return "Avion{" +
                "model='" + model + '\'' +
                ", producator='" + producator + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", tip='" + tip + '\'' +
                ", wifi=" + (areWifi ? "da" : "nu") +
                ", inServiciu=" + (inServiciu ? "da" : "nu") +
                ", rating=" + rating +
                '}';
    }
}
