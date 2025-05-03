package models;
// src/models/Actividad.java
import java.time.LocalDate;

public class Actividad {
    // Atributos
    private String tipo;  // Tipo de actividad (ej: "RIEGO")
    private LocalDate fecha; // Fecha programada
    private boolean completada; // Estado

    // Constructor
    public Actividad(String tipo, LocalDate fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.completada = false; // Por defecto, no completada
    }

    // --- Getters y Setters ---
    public String getTipo() { return tipo; } // Obtiene el tipo
    public LocalDate getFecha() { return fecha; } // Obtiene la fecha
    public boolean isCompletada() { return completada; } // Verifica estado
    public void setCompletada(boolean completada) { this.completada = completada; } // Cambia estado
}





