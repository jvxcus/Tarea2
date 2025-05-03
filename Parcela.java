package models;

// src/models/Parcela.java
import java.util.*;

public class Parcela {
    // Atributos básicos
    private String codigo; // Identificador único (ej: "P-001")
    private double tamaño; // Tamaño en hectáreas
    private String ubicacion; // Zona geográfica
    private List<Cultivo> cultivos = new ArrayList<>(); // Cultivos asociados

    // Constructor
    public Parcela(String codigo, double tamaño, String ubicacion) {
        this.codigo = codigo;
        this.tamaño = tamaño;
        this.ubicacion = ubicacion;
    }

    // --- Getters ---
    public String getCodigo() { return codigo; }
    public double getTamaño() { return tamaño; }
    public String getUbicacion() { return ubicacion; }
    // Retorna copia de la lista para evitar modificaciones externas
    public List<Cultivo> getCultivos() { return new ArrayList<>(cultivos); }
}