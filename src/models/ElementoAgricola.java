package models;

import java.time.LocalDate;

public abstract class ElementoAgricola {
    protected String nombre;
    protected LocalDate fechaSiembra;

    public ElementoAgricola(String nombre, LocalDate fechaSiembra) {
        this.nombre = nombre;
        this.fechaSiembra = fechaSiembra;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaSiembra() {
        return fechaSiembra;
    }

    public abstract void mostrarEstado();
}
