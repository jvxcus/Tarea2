public class Cultivo extends ElementoAgricola { //public class, es visible desde cualquier parte del proyecto, se puede usar desde otro paquete.
    private String variedad; //sólo se puede usar en clases internas (clases dentro de clases).
    private double superficie; //tipo de dato primitivo que representa un número con décimales
    private Parcela parcela; //declaración de variable
    private String estado; //declaración de variable
    private List<Actividad> actividades; //Lista de objetos tipo Actividad, usa la interfaz de List de Java, actividades, el nombre de ese atributo
}