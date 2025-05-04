package models;

public class Parcela {
    private String codigo;
    private double tamaño;
    private String ubicacion;

    public Parcela(String codigo, double tamaño, String ubicacion) {
        this.codigo = codigo;
        this.tamaño = tamaño;
        this.ubicacion = ubicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Parcela: " + codigo + ", " + tamaño + " ha, ubicación: " + ubicacion;
    }
}
