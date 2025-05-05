package services;

import models.Actividad;
import models.Cultivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActividadService {

    private List<Actividad> actividades = new ArrayList<>();
    private CSVService csvService = new CSVService();

    public List<Actividad> cargarActividades() {
        actividades = csvService.leerActividades();
        return actividades;
    }

    public void guardarActividades() {
        csvService.guardarActividades(actividades);
    }

    public void listarActividades(Scanner scanner, CultivoService cultivoService) {
        if (actividades.isEmpty()) {
            this.cargarActividades();
        }

        for (Actividad a : actividades) {
            System.out.println(a);
        }
    }

    public void registrarActividad(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre de la Actividad a registrar: ");
        String tipo = scanner.nextLine();

        System.out.print("Fecha de la Actividad (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        Actividad actividad = new Actividad(tipo, fecha);
        actividades.add(actividad);
        this.guardarActividades();
        System.out.println("Actividad registrada");
    }

    public void eliminarActividad(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre de la Actividad a eliminar: ");
        String nombre = scanner.nextLine();

        Actividad actividad = buscarPorNombre(nombre);
        if (actividad != null) {
            actividades.remove(actividad);
            this.guardarActividades();
            System.out.println("Actividad eliminada.");
        } else {
            System.out.println("No se encontró la Actividad.");
        }
    }

    // Método auxiliar para buscar una actividad por nombre
    private Actividad buscarPorNombre(String nombre) {
        for (Actividad a : actividades) {
            if (a.getTipo().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        return null;
    }

    public void marcarActividadCompletada(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre del cultivo: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarPorNombre(nombre);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        System.out.print("Tipo de actividad: ");
        String tipo = scanner.nextLine();

        System.out.print("Fecha de la actividad (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        for (Actividad a : cultivo.getActividades()) {
            if (a.getTipo().equalsIgnoreCase(tipo) && a.getFecha().equals(fecha)) {
                a.setCompletada(true);
                System.out.println("Actividad marcada como completada.");
                return;
            }
        }

        System.out.println("No se encontró la actividad.");
    }
}
