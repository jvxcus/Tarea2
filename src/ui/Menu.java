public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private CultivoService cultivoService;

    public void mostrarMenu() { //
        int opcion = 0;
        do {
            System.out.println("1. Listar cultivos");
            System.out.println("2. Crear cultivo");
            System.out.println("3. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cultivoService.listarCultivos();
                    break;
                case 2:
                    cultivoService.crearCultivo();
                    break;
                case 3:
                    cultivoService.guardarCambios();  // escribe el CSV
                    break;
            }
        } while (opcion != 3);
    }
}
