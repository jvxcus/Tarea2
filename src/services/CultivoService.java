package services;

import models.Cultivo;
import models.EstadoCultivo;
import models.Parcela;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CultivoService {

    private List<Cultivo> cultivos = new ArrayList<>();
    private CSVService csvService = new CSVService();


    public List<Cultivo> cargarCultivos() {
        cultivos = csvService.leerCultivos();
        return cultivos;
    }

    public void guardarCultivos() {
        csvService.guardarCultivos(cultivos);
    }

    public void listarCultivos() {
        if (cultivos.isEmpty()) {
            this.cargarCultivos();
            for (Cultivo c : cultivos) {
                System.out.println(c);
            }
        } else {
            for (Cultivo c : cultivos) {
                System.out.println(c);
            }
        }
    }

    public void crearCultivo(Scanner scanner) {
        System.out.print("Nombre del cultivo: ");
        String nombreCultivo = scanner.nextLine();

        System.out.print("Variedad: ");
        String variedadCultivo = scanner.nextLine();

        System.out.print("Superficie (ha): ");
        double superficieCultivo = Double.parseDouble(scanner.nextLine().replace(",", ".")); // por si usan coma

        System.out.print("Código de parcela (puede dejarse vacío): ");
        String codigoParcela = scanner.nextLine();

        System.out.print("Fecha de siembra (YYYY-MM-DD): ");
        LocalDate fechaSiembra = LocalDate.parse(scanner.nextLine());

        System.out.print("Estado (ACTIVO, EN_RIESGO, COSECHADO): ");
        EstadoCultivo estado = EstadoCultivo.valueOf(scanner.nextLine().trim().toUpperCase());

        Cultivo cultivo = new Cultivo(nombreCultivo, variedadCultivo, superficieCultivo,
                codigoParcela.isEmpty() ? null : new Parcela(codigoParcela, 0, ""), // solo si hay código
                fechaSiembra, estado);

        cultivos.add(cultivo);
        this.guardarCultivos();
        System.out.println("Cultivo creado");

    }

    public void eliminarCultivo(Scanner scanner) {
        System.out.print("Nombre del cultivo a eliminar: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = buscarPorNombre(nombre);
        if (cultivo != null) {
            cultivos.remove(cultivo);
            this.guardarCultivos();
            System.out.println("Cultivo eliminado.");
        } else {
            System.out.println("No se encontró un cultivo con ese nombre.");
        }
    }

    public void editarCultivo(Scanner scanner) {
        System.out.print("Nombre del cultivo a editar: ");
        String nombre = scanner.nextLine();

        Cultivo cultivo = buscarPorNombre(nombre);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        System.out.print("Nueva variedad: ");
        cultivo.setVariedad(scanner.nextLine());

        System.out.print("Nueva superficie (ha): ");
        cultivo.setSuperficie(Double.parseDouble(scanner.nextLine().replace(",", ".")));

        System.out.print("Nuevo código de parcela: ");
        String nuevoCodigo = scanner.nextLine();
        if (!nuevoCodigo.isEmpty()) {
            cultivo.setParcela(new Parcela(nuevoCodigo, 0, ""));
        }

        System.out.print("Nueva fecha de siembra (YYYY-MM-DD): ");
        cultivo.setFechaSiembra(LocalDate.parse(scanner.nextLine()));

        System.out.print("Nuevo estado (ACTIVO, EN_RIESGO, COSECHADO): ");
        cultivo.setEstado(EstadoCultivo.valueOf(scanner.nextLine().trim().toUpperCase()));

        this.guardarCultivos();
        System.out.println("Cultivo actualizado.");
    }

    public Cultivo buscarPorNombre(String nombre) {
        this.cargarCultivos();

        for (Cultivo c : cultivos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public void agregarCultivo(Cultivo cultivo) {
        if (cultivo != null) {
            cultivos.add(cultivo);
        }
    }

    public void reporteCultivos(Scanner scanner) {
        this.cargarCultivos();  // Asegura datos actualizados

        Map<EstadoCultivo, List<Cultivo>> agrupados = new HashMap<>();

        for (Cultivo cultivo : cultivos) {
            EstadoCultivo estado = cultivo.getEstado();
            agrupados.putIfAbsent(estado, new ArrayList<>());
            agrupados.get(estado).add(cultivo);
        }

        for (EstadoCultivo estado : agrupados.keySet()) {
            List<Cultivo> lista = agrupados.get(estado);
            double superficieTotal = 0;

            for (Cultivo c : lista) {
                superficieTotal += c.getSuperficie();
            }

            System.out.println("Estado: " + estado);
            System.out.println("Cantidad de cultivos: " + lista.size());
            System.out.printf("Superficie total: %.2f ha%n", superficieTotal);
            System.out.println("----------------------------");
        }
    }


    public List<Cultivo> getCultivos() {
        return cultivos;
    }
}
