package app;

import services.CultivoService;
import services.ParcelaService;
import services.ActividadService;
import ui.Menu;

public class App2 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Debe indicar la ruta al archivo CSV de cultivos.");
            return;
        }

        String archivoCSV = args[0];

        CultivoService cultivoService = new CultivoService(archivoCSV);
        cultivoService.cargarCultivos();

        ParcelaService parcelaService = new ParcelaService(); // sin persistencia
        ActividadService actividadService = new ActividadService();

        Menu menu = new Menu(cultivoService, parcelaService, actividadService);
        menu.mostrarMenu();

        cultivoService.guardarCultivos(); // guarda solo los cultivos
        System.out.println("Programa finalizado.");
    }
}
