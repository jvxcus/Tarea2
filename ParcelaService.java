package services;

// src/services/ParcelaService.java
import models.Parcela;
import models.Cultivo;
import java.util.*;

public class ParcelaService {
    // Lista en memoria para almacenar parcelas
    private List<Parcela> parcelas = new ArrayList<>();

    // Añade una nueva parcela al sistema
    public void agregarParcela(String codigo, double tamaño, String ubicacion) {
        parcelas.add(new Parcela(codigo, tamaño, ubicacion));
    }

    // Busca una parcela por su código (no sensible a mayúsculas)
    public Parcela buscarPorCodigo(String codigo) {
        for (Parcela p : parcelas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null; // Retorna null si no existe
    }

    // Asigna un cultivo a una parcela existente
    public boolean asignarCultivoAParcela(String codigoParcela, Cultivo cultivo) {
        Parcela parcela = buscarPorCodigo(codigoParcela);
        if (parcela != null) {
            parcela.getCultivos().add(cultivo);
            return true; // Asignación exitosa
        }
        return false; // Parcela no encontrada
    }

    // Retorna una copia de todas las parcelas
    public List<Parcela> obtenerTodas() {
        return new ArrayList<>(parcelas); // Copia defensiva
    }
    //Metodo para eliminar una parcela
    public boolean eliminarParcela(String codigo) {
        Parcela parcela = buscarPorCodigo(codigo);
        if (parcela != null && parcela.getCultivos().isEmpty()) {
            parcelas.remove(parcela);
            return true; // Eliminación exitosa
        }
        return false; // No se puede eliminar si tiene cultivos
    }
    //Metodo para editar la info de una parcela (tamano, ubicacion)
    public boolean editarParcela(String codigo, double nuevoTamaño, String nuevaUbicacion) {
        Parcela parcela = buscarPorCodigo(codigo);
        if (parcela != null) {
            parcela.setTamaño(nuevoTamaño);
            parcela.setUbicacion(nuevaUbicacion);
            return true; // Edición exitosa
        }
        return false; // No se encontró la parcela
    }
}
