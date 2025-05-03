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
}