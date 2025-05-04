package services;

// src/services/ActividadService.java
// Importa las clases necesarias desde la carpeta 'models'
import models.Actividad;
import models.Cultivo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActividadService {
    // Dependencia: Necesita CultivoService para buscar cultivos
    private CultivoService cultivoService;

    // Constructor: Recibe CultivoService al crearse
    public ActividadService(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    // Método para agregar una actividad a un cultivo
    public boolean agregarActividad(String nombreCultivo, String tipo, LocalDate fecha) {
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo); // Busca el cultivo
        if (cultivo != null) {
            cultivo.getActividades().add(new Actividad(tipo, fecha)); // Agrega la actividad
            return true; // Éxito
        }
        return false; // Fallo (cultivo no encontrado)
    }

    // Método para obtener todas las actividades de un cultivo
    public List<Actividad> obtenerActividades(String nombreCultivo) {
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        return cultivo != null ? cultivo.getActividades() : new ArrayList<>(); // Retorna lista vacía si no existe
    }
    //Metodo para eliminar una actividad segun tipo y fecha
    public boolean eliminarActividad(String nombreCultivo, String tipo, LocalDate fecha) {
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        if (cultivo != null) {
            return cultivo.getActividades().removeIf(
                a -> a.getTipo().equalsIgnoreCase(tipo) && a.getFecha().equals(fecha)
            );
    }
    return false;
    //Metodo para marcar como completada una actividad segun tipo y fecha
    public boolean marcarComoCompletada(String nombreCultivo, String tipo, LocalDate fecha) {
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        if (cultivo != null) {
            for (Actividad a : cultivo.getActividades()) {
                if (a.getTipo().equalsIgnoreCase(tipo) && a.getFecha().equals(fecha)) {
                    a.setCompletada(true);
                    return true;
                }
            }
        }
        return false;
}
}
