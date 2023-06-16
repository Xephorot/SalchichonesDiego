package com.example.salchichonesdiego.Combos;

import android.os.Parcel;
import android.os.Parcelable;

public class Combo implements Parcelable {
    private String nombre;
    private double precio;
    private String descripcion;
    private String ingredientes;

    public Combo(String nombre, double precio, String descripcion, String ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
    }

    protected Combo(Parcel in) {
        nombre = in.readString();
        precio = in.readDouble();
        descripcion = in.readString();
        ingredientes = in.readString();
    }

    public static final Creator<Combo> CREATOR = new Creator<Combo>() {
        @Override
        public Combo createFromParcel(Parcel in) {
            return new Combo(in);
        }

        @Override
        public Combo[] newArray(int size) {
            return new Combo[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeString(descripcion);
        dest.writeString(ingredientes);
    }
}
