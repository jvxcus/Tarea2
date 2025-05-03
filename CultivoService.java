package services;

// src/services/CultivoService.java
import models.Cultivo;
import java.util.ArrayList;
import java.util.List;

public class CultivoService {
    // Lista que almacena todos los cultivos
    private List<Cultivo> cultivos = new ArrayList<>();

    // Busca un cultivo por nombre (ignora mayúsculas/minúsculas)
    public Cultivo buscarPorNombre(String nombre) {
        for (Cultivo c : cultivos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c; // Retorna el cultivo si lo encuentra
            }
        }
        return null; // Retorna null si no existe
    }

    // Agrega un nuevo cultivo al sistema
    public void agregarCultivo(Cultivo cultivo) {
        if (cultivo != null) {
            cultivos.add(cultivo); // Añade a la lista
        }
    }
}

