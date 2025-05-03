package services;

// src/services/CSVService.java
import models.Actividad;
import models.Cultivo;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVService {
    // Formato de fecha para el archivo CSV (ej: "2023-05-20")
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Lee todos los cultivos desde un archivo CSV
    public List<Cultivo> leerCultivos(String rutaArchivo) throws IOException {
        List<Cultivo> cultivos = new ArrayList<>();
        
        // Convertir ruta String a objeto Path (compatible con Java 8+)
        Path path = Paths.get(rutaArchivo);
        
        // Leer todas las líneas del archivo
        List<String> lineas = Files.readAllLines(path);

        for (String linea : lineas) {
            // Saltar líneas vacías
            if (linea.trim().isEmpty()) continue;
            
            // Dividir la línea por comas, ignorando las dentro de comillas
            String[] partes = linea.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            
            try {
                // Extraer y limpiar cada campo
                String nombre = partes[0].replace("\"", "");
                String variedad = partes[1].replace("\"", "");
                double superficie = Double.parseDouble(partes[2]);
                String parcela = partes[3].replace("\"", "");
                // Convertir String a LocalDate
                LocalDate fechaSiembra = LocalDate.parse(partes[4].replace("\"", ""), DATE_FORMATTER);
                String estado = partes[5].replace("\"", "");

                // Crear objeto Cultivo
                Cultivo cultivo = new Cultivo(nombre, variedad, superficie, parcela, fechaSiembra, estado);
                
                // Procesar actividades si existen (partes[6])
                if (partes.length > 6) {
                    String actividadesStr = partes[6].replace("\"", "").replace("[", "").replace("]", "");
                    if (!actividadesStr.isEmpty()) {
                        // Dividir actividades por punto y coma
                        for (String act : actividadesStr.split(";")) {
                            String[] datosAct = act.split(":");
                            if (datosAct.length >= 2) {
                                // Añadir cada actividad al cultivo
                                cultivo.agregarActividad(new Actividad(
                                    datosAct[0], // Tipo (ej: "RIEGO")
                                    LocalDate.parse(datosAct[1], DATE_FORMATTER) // Fecha
                                ));
                            }
                        }
                    }
                }
                
                cultivos.add(cultivo);
            } catch (Exception e) {
                System.err.println("Error procesando línea: " + linea);
                e.printStackTrace();
            }
        }
        return cultivos;
    }

    // Guarda la lista de cultivos en un archivo CSV
    public void guardarCultivos(List<Cultivo> cultivos, String rutaArchivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Cultivo c : cultivos) {
                StringBuilder sb = new StringBuilder();
                // Construir línea CSV con comillas
                sb.append("\"").append(c.getNombre()).append("\",");
                sb.append("\"").append(c.getVariedad()).append("\",");
                sb.append(c.getSuperficie()).append(",");
                sb.append("\"").append(c.getCodigoParcela()).append("\",");
                sb.append("\"").append(c.getFechaSiembra().format(DATE_FORMATTER)).append("\",");
                sb.append("\"").append(c.getEstado()).append("\",");
                
                // Serializar actividades en formato [TIPO:FECHA;TIPO2:FECHA2]
                sb.append("\"[");
                for (int i = 0; i < c.getActividades().size(); i++) {
                    Actividad a = c.getActividades().get(i);
                    sb.append(a.getTipo()).append(":").append(a.getFecha().format(DATE_FORMATTER));
                    if (i < c.getActividades().size() - 1) sb.append(";");
                }
                sb.append("]\"");

                writer.println(sb.toString());
            }
        }
    }
}