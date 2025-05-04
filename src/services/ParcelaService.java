package services;

import models.Cultivo;
import models.Parcela;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParcelaService {

    private List<Parcela> parcelas = new ArrayList<>();

    public void agregarParcela(Scanner scanner) {
        System.out.print("Código de la parcela: ");
        String codigo = scanner.nextLine();

        System.out.print("Tamaño en hectáreas: ");
        double tamaño = Double.parseDouble(scanner.nextLine().replace(",", "."));

        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        parcelas.add(new Parcela(codigo, tamaño, ubicacion));
        System.out.println("Parcela agregada.");
    }

    public void listarParcelas() {
        if (parcelas.isEmpty()) {
            System.out.println("No hay parcelas registradas.");
        } else {
            for (Parcela p : parcelas) {
                System.out.println(p);
            }
        }
    }

    public void eliminarParcela(Scanner scanner) {
        System.out.print("Código de la parcela a eliminar: ");
        String codigo = scanner.nextLine();

        Parcela parcela = buscarPorCodigo(codigo);

        if (parcela != null) {
            parcelas.remove(parcela);
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
        for (Parcela p : parcelas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
