package ui;

import java.util.List;
import java.util.ArrayList;
import models.Cultivo;

import services.CultivoService;
import services.ParcelaService;
import services.ActividadService;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final CultivoService cultivoService = new CultivoService();
    private final ParcelaService parcelaService = new ParcelaService();
    private final ActividadService actividadService = new ActividadService();

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("------------------- Menú -------------------");
            System.out.println("[ 1 ]   Gestión de Cultivos");
            System.out.println("[ 2 ]   Gestión de Parcelas");
            System.out.println("[ 3 ]   Gestión de Actividades");
            System.out.println("[ 4 ]   Reportes");
            System.out.println("[ 0 ]   Salir");
            System.out.print("\nOpción: ");
            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> menuCultivos();
                case 2 -> menuParcelas();
                case 3 -> menuActividades();
                case 4 -> menuReportes();
                case 0 -> {
                    cultivoService.guardarCultivos();
                    parcelaService.guardarParcelas();
                    actividadService.guardarActividades();
                    System.out.println("Ejecución finalizada");
                }
                default -> System.out.println("Opción inválida, intentelo de nuevo");
            }
        } while (opcion != 0);
    }

    private void menuCultivos() {
        System.out.println("-- Gestión de Cultivos --");
        System.out.println("[ 1 ]   Listar cultivos");
        System.out.println("[ 2 ]   Crear cultivo");
        System.out.println("[ 3 ]   Eliminar cultivo");
        System.out.println("[ 4 ]   Editar cultivo");
        System.out.println("[ 5 ]   Buscar cultivo por nombre o variedad");
        System.out.println("[ 6 ]   Reporte de cultivos (ACTIVOS, COSECHADOS, EN RIESGO)");
        System.out.print("\nOpción: ");
        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> cultivoService.listarCultivos();
            case 2 -> cultivoService.crearCultivo(scanner);
            case 3 -> cultivoService.eliminarCultivo(scanner);
            case 4 -> cultivoService.editarCultivo(scanner);
            case 5 -> buscarCultivoPorNombreOVariedad();
            case 6 -> cultivoService.reporteCultivos(scanner);
            default -> System.out.println("Opción inválida.");
        }
    }


    private void buscarCultivoPorNombreOVariedad() {
        System.out.print("Ingrese el término a buscar (nombre o variedad): ");
        String termino = scanner.nextLine().trim().toLowerCase();

        List<Cultivo> resultados = new ArrayList<>();
        for (Cultivo cultivo : cultivoService.getCultivos()) {
            if (cultivo.getNombre().toLowerCase().contains(termino) ||
                    cultivo.getVariedad().toLowerCase().contains(termino)) {
                resultados.add(cultivo);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
            System.out.println("Cultivos disponibles:");
            cultivoService.listarCultivos(); // Mostrar los nombres existentes como guía
        } else {
            System.out.println("Resultados encontrados:");
            for (Cultivo c : resultados) {
                System.out.println(c);
            }
        }
    }
    private void menuParcelas() {
        System.out.println("-- Gestión de Parcelas --");
        System.out.println("[ 1 ]   Listar parcelas");
        System.out.println("[ 2 ]   Agregar parcela");
        System.out.println("[ 3 ]   Eliminar parcela");
        System.out.println("[ 4 ]   Editar parcela");
        System.out.println("[ 5 ]   Asignar cultivo a parcela");
        System.out.print("\nOpción: ");
        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> parcelaService.listarParcelas();
            case 2 -> parcelaService.crearParcela(scanner);
            case 3 -> parcelaService.eliminarParcela(scanner);
            case 4 -> parcelaService.editarParcela(scanner);
            case 5 -> parcelaService.asignarCultivoAParcela(scanner, cultivoService);
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuActividades() {
        System.out.println("-- Gestión de Actividades --");
        System.out.println("[ 1 ]   Registrar actividad");
        System.out.println("[ 2 ]   Listar actividades");
        System.out.println("[ 3 ]   Eliminar actividad");
        System.out.println("[ 4 ]   Marcar como completada");
        System.out.print("\nOpción: ");
        int opcion = leerOpcion();

        switch (opcion) {
            case 1 -> actividadService.registrarActividad(scanner, cultivoService);
            case 2 -> actividadService.listarActividades(scanner, cultivoService);
            case 3 -> actividadService.eliminarActividad(scanner, cultivoService);
            case 4 -> actividadService.marcarActividadCompletada(scanner, cultivoService);
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuReportes() {
        System.out.println("-- Módulo de Reportes --");
        System.out.println("(Funcionalidad no implementada todavía)");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // valor inválido
        }
    }
}
