package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cultivo extends ElementoAgricola {
    private String variedad;
    private double superficie;
    private Parcela parcela;
    private EstadoCultivo estado;
    private List<Actividad> actividades;

    public Cultivo(String nombre, String variedad, double superficie, Parcela parcela, LocalDate fechaSiembra, EstadoCultivo estado) {
        super(nombre, fechaSiembra);
        this.variedad = variedad;
        this.superficie = superficie;
        this.parcela = parcela;
        this.estado = estado;
        this.actividades = new ArrayList<>();
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public EstadoCultivo getEstado() {
        return estado;
    }

    public void setEstado(EstadoCultivo estado) {
        this.estado = estado;
    }

    public void setFechaSiembra(LocalDate fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public void agregarActividad(Actividad act) {
        actividades.add(act);
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Estado del cultivo: " + estado);
    }

    @Override
    public String toString() {
        String parcelaInfo = (parcela != null) ? parcela.getCodigo() : "sin asignar";
        return "Cultivo: " + nombre + " (" + variedad + "), " + superficie + " ha, estado: " + estado + ", parcela: " + parcelaInfo;
    }
}
