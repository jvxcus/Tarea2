package models;

// NO necesita paquete (o 'package models;')

import java.util.ArrayList;
import java.util.List;

public class Cultivo {
    // Atributos
    private String nombre; // Nombre del cultivo (ej: "Maíz")
    private List<Actividad> actividades = new ArrayList<>(); // Lista de actividades asociadas

    // Constructor
    public Cultivo(String nombre) {
        this.nombre = nombre;
    }

    // --- Getters ---
    public String getNombre() { return nombre; } // Obtiene el nombre
    public List<Actividad> getActividades() { return actividades; } // Obtiene actividades
}