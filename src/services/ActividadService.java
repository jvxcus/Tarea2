package services;

import models.Actividad;
import models.Cultivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActividadService {
    private CultivoService cultivoService;

    public ActividadService() {}

    public ActividadService(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    // Agrega una nueva actividad desde consola
    public void registrarActividad(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre del cultivo: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarPorNombre(nombre);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        System.out.print("Tipo de actividad (RIEGO, FERTILIZACION, etc.): ");
        String tipo = scanner.nextLine();

        System.out.print("Fecha de la actividad (YYYY-MM-DD): ");
        String fechaTexto = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaTexto);

        cultivo.agregarActividad(new Actividad(tipo, fecha));
        System.out.println("Actividad registrada.");
    }

    // Lista todas las actividades de un cultivo
    public void listarActividades(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre del cultivo: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarPorNombre(nombre);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        List<Actividad> actividades = cultivo.getActividades();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades registradas.");
        } else {
            for (Actividad a : actividades) {
                System.out.println(a);
            }
        }
    }

    // Elimina una actividad específica por tipo y fecha
    public void eliminarActividad(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre del cultivo: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarPorNombre(nombre);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        System.out.print("Tipo de actividad a eliminar: ");
        String tipo = scanner.nextLine();
        System.out.print("Fecha de la actividad a eliminar (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        boolean eliminada = cultivo.getActividades().removeIf(a ->
                a.getTipo().equalsIgnoreCase(tipo) && a.getFecha().equals(fecha)
        );

        if (eliminada) {
            System.out.println("Actividad eliminada.");
        } else {
            System.out.println("No se encontró la actividad.");
        }
    }

    // Marca una actividad como completada
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

    // Métodos alternativos programáticos

    public boolean agregarActividad(String nombreCultivo, String tipo, LocalDate fecha) {
        if (cultivoService == null) return false;
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        if (cultivo != null) {
            cultivo.getActividades().add(new Actividad(tipo, fecha));
            return true;
        }
        return false;
    }

    public List<Actividad> obtenerActividades(String nombreCultivo) {
        if (cultivoService == null) return new ArrayList<>();
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        return cultivo != null ? cultivo.getActividades() : new ArrayList<>();
    }

    public boolean eliminarActividad(String nombreCultivo, String tipo, LocalDate fecha) {
        if (cultivoService == null) return false;
        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        if (cultivo != null) {
            return cultivo.getActividades().removeIf(
                    a -> a.getTipo().equalsIgnoreCase(tipo) && a.getFecha().equals(fecha)
            );
        }
        return false;
    }

    public boolean marcarComoCompletada(String nombreCultivo, String tipo, LocalDate fecha) {
        if (cultivoService == null) return false;
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
