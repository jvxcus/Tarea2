package services;

import models.Cultivo;
import models.EstadoCultivo;
import models.Parcela;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParcelaService {

    private List<Parcela> parcelas = new ArrayList<>();
    private final CSVService csvService = new CSVService();

    public List<Parcela> cargarParcelas() {
        parcelas = csvService.leerParcelas();
        return parcelas;
    }


    public void guardarParcelas() {
        csvService.guardarParcelas(parcelas);
    }

    public void listarParcelas() {
        if (parcelas.isEmpty()) {
            this.cargarParcelas();
            for (Parcela p : parcelas) {
                System.out.println(p);
            }
        } else {
            for (Parcela p : parcelas) {
                System.out.println(p);
            }
        }
    }

    public void crearParcela(Scanner scanner) {
        System.out.print("Código de la parcela: ");
        String codigo = scanner.nextLine();

        System.out.print("Tamaño en hectáreas: ");
        double tamaño = Double.parseDouble(scanner.nextLine().replace(",", "."));

        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        Parcela parcela = new Parcela(codigo, tamaño, ubicacion);

        parcelas.add(parcela);
        this.guardarParcelas();
        System.out.println("Parcela creada.");

    }

    public void eliminarParcela(Scanner scanner) {
        System.out.print("Código de la parcela a eliminar: ");
        String codigo = scanner.nextLine();

        Parcela parcela = buscarPorCodigo(codigo);
        if (parcela != null) {
            parcelas.remove(parcela);
            this.guardarParcelas();
            System.out.println("Parcela eliminada.");
        } else {
            System.out.println("No se encontró una parcela con ese código.");
        }
    }

    public void editarParcela(Scanner scanner) {
        System.out.print("Código de la parcela a editar: ");
        String codigo = scanner.nextLine();

        Parcela parcela = buscarPorCodigo(codigo);
        if (parcela == null) {
            System.out.println("Parcela no encontrada.");
            return;
        }

        System.out.print("Nuevo tamaño (ha): ");
        parcela.setTamaño(Double.parseDouble(scanner.nextLine().replace(",", ".")));

        System.out.print("Nueva ubicación: ");
        parcela.setUbicacion(scanner.nextLine());

        System.out.println("Parcela actualizada.");
    }

    public void asignarCultivoAParcela(Scanner scanner, CultivoService cultivoService) {
        System.out.print("Nombre del cultivo: ");
        String nombreCultivo = scanner.nextLine();

        Cultivo cultivo = cultivoService.buscarPorNombre(nombreCultivo);
        if (cultivo == null) {
            System.out.println("Cultivo no encontrado.");
            return;
        }

        System.out.print("Código de la parcela a asignar: ");
        String codigoParcela = scanner.nextLine();

        Parcela parcela = buscarPorCodigo(codigoParcela);
        if (parcela == null) {
            System.out.println("Parcela no encontrada.");
            return;
        }

        cultivo.setParcela(parcela);
        System.out.println("Parcela asignada al cultivo.");
    }

    public Parcela buscarPorCodigo(String codigo) {
        this.cargarParcelas();

        for (Parcela p : parcelas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void agregarParcela(Parcela parcela) {
        if (parcela != null) {
            parcelas.add(parcela);
        }
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }




}
