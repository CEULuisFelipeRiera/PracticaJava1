package Procesamiento;
import java.util.*;

public class Experimento {
    private int idExperimento;
    private String nombreExp;
    private ArrayList<Poblacion> poblaciones;

    public Experimento(int idExperimento, String nombreExp, List<Poblacion> poblaciones) {
        this.idExperimento = idExperimento;
        this.nombreExp = nombreExp;
        this.poblaciones = new ArrayList<>();
    }

    public void agregarPoblacion(Poblacion poblacion) {
        this.poblaciones.add(poblacion);
    }

    public void crearPoblacion(String nombre, int idPoblacion, Date fechaInicio, Date fechaFin, int numBacterias, float temperatura, Luminosidad luminosidad, Dosis dosisComida) {
        Poblacion poblacion1 = new Poblacion(nombre, idPoblacion, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad, dosisComida);
        agregarPoblacion(poblacion1);
    }

    public void borrarPoblacion(Poblacion poblacion) {
        this.poblaciones.remove(poblacion);
    }

    public void mostrarPoblacion() {
        for (Poblacion poblacion : poblaciones) {
            System.out.println(poblacion);
        }
    }

    @Override
    public String toString() {
        return "Experimento{" +
                "idExperimento=" + idExperimento +
                ", nombreExp='" + nombreExp + '\'' +
                ", poblaciones=" + poblaciones +
                '}';
    }

    public int getIdExperimento() {
        return idExperimento;
    }

    public void setIdExperimento(int idExperimento) {
        this.idExperimento = idExperimento;
    }

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public ArrayList<Poblacion> getPoblaciones() {
        return poblaciones;
    }

    public void setPoblaciones(ArrayList<Poblacion> poblaciones) {
        this.poblaciones = poblaciones;
    }
}