package Procesamiento;
import java.util.Date;
import java.util.Objects;

public class Poblacion {
    public String nombre;
    public int  idPoblacion;
    public Date fechaInicio;
    public Date fechaFin;
    public int numBacterias;
    public float temperatura;
    public Luminosidad luminosidad;
    public Dosis dosisComida;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poblacion)) return false;
        Poblacion poblacion = (Poblacion) o;
        return idPoblacion == poblacion.idPoblacion && getNumBacterias() == poblacion.getNumBacterias() && Float.compare(getTemperatura(), poblacion.getTemperatura()) == 0 && Objects.equals(getNombre(), poblacion.getNombre()) && Objects.equals(getFechaInicio(), poblacion.getFechaInicio()) && Objects.equals(getFechaFin(), poblacion.getFechaFin()) && getLuminosidad() == poblacion.getLuminosidad() && Objects.equals(getDosisComida(), poblacion.getDosisComida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), idPoblacion, getFechaInicio(), getFechaFin(), getNumBacterias(), getTemperatura(), getLuminosidad(), getDosisComida());
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

    public void setNumBacteriasIniciales(int numBacterias) {
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
