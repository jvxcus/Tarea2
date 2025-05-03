package models;

// src/models/Cultivo.java
import java.time.LocalDate;
import java.util.*;

public class Cultivo {
    // Atributos
    private String nombre; // Ej: "Maíz"
    private String variedad; // Ej: "Dulce"
    private double superficie; // En hectáreas
    private String codigoParcela; // Parcela asignada
    private LocalDate fechaSiembra;
    private String estado; // "ACTIVO", "COSECHADO", etc.
    private List<Actividad> actividades = new ArrayList<>();

    // Constructor mínimo
    public Cultivo(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo para CSV
    public Cultivo(String nombre, String variedad, double superficie, 
                  String codigoParcela, LocalDate fechaSiembra, String estado) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.superficie = superficie;
        this.codigoParcela = codigoParcela;
        this.fechaSiembra = fechaSiembra;
        this.estado = estado;
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getVariedad() { return variedad; }
    public void setVariedad(String variedad) { this.variedad = variedad; }
    
    public double getSuperficie() { return superficie; }
    public void setSuperficie(double superficie) { this.superficie = superficie; }
    
    public String getCodigoParcela() { return codigoParcela; }
    public void setCodigoParcela(String codigoParcela) { this.codigoParcela = codigoParcela; }
    
    public LocalDate getFechaSiembra() { return fechaSiembra; }
    public void setFechaSiembra(LocalDate fechaSiembra) { this.fechaSiembra = fechaSiembra; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    // Retorna copia defensiva de actividades
    public List<Actividad> getActividades() { return new ArrayList<>(actividades); }

    // --- Métodos de gestión ---
    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public boolean eliminarActividad(Actividad actividad) {
        return actividades.remove(actividad);
    }
}