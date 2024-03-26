package Procesamiento;
import java.util.Date;
import java.util.Objects;

/**
 * The Poblacion class represents a population in an experiment.
 * It contains information about the population such as its name, id, start and end dates, number of bacteria, temperature, luminosity, and food dose.
 */
public class Poblacion {
    // The name of the population
    public String nombre;
    // The id of the population
    public int  idPoblacion;
    // The start date of the population
    public Date fechaInicio;
    // The end date of the population
    public Date fechaFin;
    // The number of bacteria in the population. It's an Integer because it needs to be null in the editPoblacion method
    public Integer numBacterias;
    // The temperature of the population
    public float temperatura;
    // The luminosity of the population
    public Luminosidad luminosidad;
    // The food dose of the population
    public Dosis dosisComida;

    /**
     * Constructs a new Poblacion with the given parameters.
     * @param nombre The name of the population
     * @param idPoblacion The id of the population
     * @param fechaInicio The start date of the population
     * @param fechaFin The end date of the population
     * @param numBacterias The number of bacteria in the population
     * @param temperatura The temperature of the population
     * @param luminosidad The luminosity of the population
     * @param dosisComida The food dose of the population
     */
    public Poblacion(String nombre, int idPoblacion, Date fechaInicio, Date fechaFin, int numBacterias,
                     float temperatura, Luminosidad luminosidad, Dosis dosisComida) {
        this.nombre = nombre;
        this.idPoblacion = idPoblacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacterias = numBacterias;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
    }

    /**
     * Returns a string representation of the Poblacion.
     * @return A string representation of the Poblacion
     */
    @Override
    public String toString() {
        return "Poblacion{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", numBacteriasIniciales=" + numBacterias +
                ", temperatura=" + temperatura +
                ", luminosidad='" + luminosidad + '\'' +
                ", dosisComida=" + dosisComida +
                '}';
    }

    /**
     * Checks if this Poblacion is equal to another object.
     * @param o The object to compare this Poblacion to
     * @return true if the objects are the same; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poblacion)) return false;
        Poblacion poblacion = (Poblacion) o;
        return idPoblacion == poblacion.idPoblacion && getNumBacterias() == poblacion.getNumBacterias() && Float.compare(getTemperatura(), poblacion.getTemperatura()) == 0 && Objects.equals(getNombre(), poblacion.getNombre()) && Objects.equals(getFechaInicio(), poblacion.getFechaInicio()) && Objects.equals(getFechaFin(), poblacion.getFechaFin()) && getLuminosidad() == poblacion.getLuminosidad() && Objects.equals(getDosisComida(), poblacion.getDosisComida());
    }

    /**
     * Returns a hash code value for the Poblacion.
     * @return A hash code value for the Poblacion
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), idPoblacion, getFechaInicio(), getFechaFin(), getNumBacterias(), getTemperatura(), getLuminosidad(), getDosisComida());
    }

    // Getters and setters for the Poblacion's fields
    public int getIdPoblacion() {
        return idPoblacion;
    }

    public void setIdPoblacion(int idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setNumBacterias(int numBacterias) {
        this.numBacterias = numBacterias;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setLuminosidad(Luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    public void setDosisComida(Dosis dosisComida) {
        this.dosisComida = dosisComida;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getNumBacterias() {
        return numBacterias;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public Luminosidad getLuminosidad() {
        return luminosidad;
    }

    public Dosis getDosisComida() {
        return dosisComida;
    }
}
