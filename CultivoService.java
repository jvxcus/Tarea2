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
    //Funcion para eliminar un cultivo
    public boolean eliminarCultivo(String nombre) {
        Cultivo cultivo = buscarPorNombre(nombre);
        if (cultivo != null && cultivo.getActividades().isEmpty()) {
            cultivos.remove(cultivo); // Elimina de la lista
            return true;
        }
        return false; // No se puede eliminar si tiene actividades
    }
    // Actualiza la info de un cultivo
    public boolean actualizarCultivo(String nombre, String nuevaVariedad, double nuevaSuperficie,
                                    String nuevoCodigoParcela, LocalDate nuevaFechaSiembra, String nuevoEstado) {
        Cultivo cultivo = buscarPorNombre(nombre);
        if (cultivo != null) {
            cultivo.setVariedad(nuevaVariedad);
            cultivo.setSuperficie(nuevaSuperficie);
            cultivo.setCodigoParcela(nuevoCodigoParcela);
            cultivo.setFechaSiembra(nuevaFechaSiembra);
            cultivo.setEstado(nuevoEstado);
            return true; // Actualización exitosa
    }
    return false; // No se encontró el cultivo
    }
}

