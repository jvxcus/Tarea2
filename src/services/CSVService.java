package services;

import models.Actividad;
import models.Cultivo;
import models.EstadoCultivo;
import models.Parcela;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CSVService {
    private final String cultivoCSV = "cultivo.csv";
    private final String parcelaCSV = "parcela.csv";
    private final String actividadCSV = "actividad.csv";

    private String limpiar(String entrada) {
        return entrada.replace("\"", "").trim();
    }

    // ==================== CULTIVOS ====================

    public List<Cultivo> leerCultivos() {
        List<Cultivo> cultivos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cultivoCSV))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                if (partes.length < 6) continue;

                String nombre = partes[0].replace("\"", "").trim();
                String variedad = partes[1].replace("\"", "").trim();
                double superficie = Double.parseDouble(partes[2].replace("\"", "").trim());
                String codigoParcela = partes[3].replace("\"", "").trim();
                LocalDate fecha = LocalDate.parse(partes[4].replace("\"", "").trim());
                EstadoCultivo estado = EstadoCultivo.valueOf(partes[5].replace("\"", "").trim());
                String actividadesTexto = partes.length > 6 ? partes[6].replaceAll("[\\[\\]\"]", "") : "";

                List<Actividad> actividades = Arrays.stream(actividadesTexto.split(","))
                        .filter(s -> s.contains(":"))
                        .map(s -> {
                            String[] act = s.split(":");
                            return new Actividad(act[0], LocalDate.parse(act[1]));
                        })
                        .collect(Collectors.toList());

                Cultivo cultivo = new Cultivo(nombre, variedad, superficie, null, fecha, estado);
                actividades.forEach(cultivo::agregarActividad);
                cultivos.add(cultivo);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error procesando línea: " + e.getMessage());
        }

        return cultivos;
    }

    public void guardarCultivos(List<Cultivo> cultivos) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(cultivoCSV))) {
            for (Cultivo c : cultivos) {
                String actividades = c.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha())
                        .collect(Collectors.joining(","));

                pw.println(String.format("\"%s\",\"%s\",%s,\"%s\",%s,%s,[%s]",
                        c.getNombre(),
                        c.getVariedad(),
                        c.getSuperficie(),
                        (c.getParcela() != null ? c.getParcela().getCodigo() : ""),
                        c.getFechaSiembra(),
                        c.getEstado(),
                        actividades));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // ==================== PARCELAS ====================

    public List<Parcela> leerParcelas() {
        List<Parcela> parcelas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(parcelaCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 3) continue;

                // Limpieza directa en línea
                String codigo = partes[0].replace("\"", "").trim();
                String tamañoTexto = partes[1].replace("\"", "").trim().replace(",", ".");
                double tamaño = Double.parseDouble(tamañoTexto);
                String ubicacion = partes[2].replace("\"", "").trim();

                parcelas.add(new Parcela(codigo, tamaño, ubicacion));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de parcelas: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error procesando parcela: " + e.getMessage());
        }

        return parcelas;
    }

    public void guardarParcelas(List<Parcela> parcelas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(parcelaCSV))) {
            for (Parcela p : parcelas) {
                pw.println(String.format("\"%s\",%s,\"%s\"", p.getCodigo(), p.getTamaño(), p.getUbicacion()));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar parcelas: " + e.getMessage());
        }
    }

    // ==================== ACTIVIDADES ====================

    public List<Actividad> leerActividades() {
        List<Actividad> actividades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(actividadCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().startsWith("Actividad")) continue;

                String[] partes = linea.split("\",?");
                String tipo = limpiar(partes[1]);
                LocalDate fecha = LocalDate.parse(limpiar(partes[2]));
                boolean completada = Boolean.parseBoolean(limpiar(partes[3]));

                Actividad actividad = new Actividad(tipo, fecha);
                actividad.setCompletada(completada);
                actividades.add(actividad);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de actividades: " + e.getMessage());
        }

        return actividades;
    }

    public void guardarActividades(List<Actividad> actividades) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(actividadCSV))) {
            for (Actividad a : actividades) {
                pw.println(String.format("Actividad,\"%s\",%s,%s",
                        a.getTipo(),
                        a.getFecha(),
                        a.isCompletada()));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar actividades: " + e.getMessage());
        }
    }
}
