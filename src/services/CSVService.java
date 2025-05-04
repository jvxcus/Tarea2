package services;

import models.Actividad;
import models.Cultivo;
import models.EstadoCultivo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVService {

    // Limpia comillas y espacios
    private String limpiar(String entrada) {
        return entrada.replace("\"", "").trim();
    }

    // Lee cultivos desde un archivo CSV
    public List<Cultivo> leerCultivos(String rutaArchivo) {
        List<Cultivo> cultivos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().startsWith("Cultivo")) continue;

                String[] partes = linea.split("\",?");

                String nombre = limpiar(partes[1]);
                String variedad = limpiar(partes[2]);
                double superficie = Double.parseDouble(limpiar(partes[3]));
                String codigoParcela = limpiar(partes[4]); // no se usa aquí directamente
                LocalDate fecha = LocalDate.parse(limpiar(partes[5]));
                EstadoCultivo estado = EstadoCultivo.valueOf(limpiar(partes[6]));
                String actividadesTexto = partes.length > 7 ? partes[7].replaceAll("[\\[\\]\"]", "") : "";

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

    // Guarda los cultivos en un archivo CSV
    public void guardarCultivos(String rutaArchivo, List<Cultivo> cultivos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Cultivo c : cultivos) {
                String actividades = c.getActividades().stream()
                        .map(a -> a.getTipo() + ":" + a.getFecha())
                        .collect(Collectors.joining(","));

                pw.println(String.format("Cultivo,\"%s\",\"%s\",%s,\"%s\",%s,%s,[%s]",
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
}
