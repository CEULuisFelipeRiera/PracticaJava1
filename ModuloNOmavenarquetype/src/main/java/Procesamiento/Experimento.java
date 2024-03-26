package Procesamiento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.io.*;
import GestionDeMemoria.*;

/**
 * The Experimento class represents an experiment in a laboratory.
 * It contains information about the experiment such as its id, name, file path, and a list of populations involved in the experiment.
 */
public class Experimento {
    // The id of the experiment
    public int idExperimento;
    // The name of the experiment
    public String nombreExp;
    // The file path of the experiment
    public String pathArchivo;
    // The list of populations involved in the experiment
    public ArrayList<Poblacion> poblaciones;

    /**
     * Constructs a new Experimento with the given parameters.
     * @param idExperimento The id of the experiment
     * @param nombreExp The name of the experiment
     * @param pathArchivo The file path of the experiment
     * @param poblaciones The list of populations involved in the experiment
     */
    public Experimento(int idExperimento, String nombreExp, String pathArchivo, List<Poblacion> poblaciones) {
        this.idExperimento = idExperimento;
        this.nombreExp = nombreExp;
        this.pathArchivo = pathArchivo;
        this.poblaciones = new ArrayList<>();
    }

    /**
     * Adds a population to the list of populations involved in the experiment.
     * @param poblacion The population to be added
     */
    public void agregarPoblacion(Poblacion poblacion) {
        this.poblaciones.add(poblacion);
    }

    /**
     * Creates a new population and adds it to the list of populations involved in the experiment.
     * @param nombre The name of the population
     * @param idPoblacion The id of the population
     * @param fechaInicio The start date of the population
     * @param fechaFin The end date of the population
     * @param numBacterias The number of bacteria in the population
     * @param temperatura The temperature of the population
     * @param luminosidad The luminosity of the population
     * @param dosisComida The food dose of the population
     */
    public void crearPoblacion(String nombre, int idPoblacion, Date fechaInicio, Date fechaFin, int numBacterias, float temperatura, Luminosidad luminosidad, Dosis dosisComida) {
        Poblacion poblacion1 = new Poblacion(nombre, idPoblacion, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad, dosisComida);
        agregarPoblacion(poblacion1);
    }

    /**
     * Removes a population from the list of populations involved in the experiment.
     * @param poblacion The population to be removed
     * @return true if the population was successfully removed; false otherwise
     */
    public boolean borrarPoblacion(Poblacion poblacion) {
        return this.poblaciones.remove(poblacion);
    }

    /**
     * Returns a string representation of a population with the given name.
     * @param nombrePoblacion The name of the population
     * @return A string representation of the population if it exists; a message indicating that the population was not found otherwise
     */
    public String mostrarPoblacion(String nombrePoblacion) {
        Poblacion poblacion = null;
        for (Poblacion p : poblaciones) {
            if (p.getNombre().equals(nombrePoblacion)) {
                poblacion = p;
                break;
            }
        }

        if (poblacion != null) {
            return poblacion.toString();
        } else {
            return "No se encontró la población con el nombre dado.";
        }
    }

    /**
     * Prints the names of all populations involved in the experiment.
     */
    public void imprimirNombresPoblaciones() {
        for (Poblacion poblacion : this.poblaciones) {
            System.out.println(poblacion.getNombre());
        }
    }

    /**
     * Edits a population based on the user's choice and the new parameters provided.
     * @param experimento The experiment containing the population to be edited
     * @param choice The index of the population to be edited
     * @param newName The new name for the population
     * @param newStartDateStr The new start date for the population
     * @param newEndDateStr The new end date for the population
     * @param newNumBacteria The new number of bacteria in the population
     * @param newTemperature The new temperature of the population
     * @param newLuminosityStr The new luminosity of the population
     * @param newDosis The new food dose of the population
     * @return A string indicating the result of the operation
     */
    public String editarPoblacion(Experimento experimento, int choice, String newName, String newStartDateStr, String newEndDateStr, Integer newNumBacteria, Float newTemperature, String newLuminosityStr, Dosis newDosis) {
        StringBuilder output = new StringBuilder();

        if (choice > 0 && choice <= experimento.poblaciones.size()) {
            Poblacion poblacion = experimento.poblaciones.get(choice - 1);

            if (newName != null) {
                poblacion.setNombre(newName);
            }

            if (newStartDateStr != null) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date newStartDate = formatter.parse(newStartDateStr);
                    poblacion.setFechaInicio(newStartDate);
                } catch (ParseException e) {
                    output.append("Invalid date format. Please enter the date in the format yyyy-MM-dd.\n");
                }
            }

            if (newEndDateStr != null) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date newEndDate = formatter.parse(newEndDateStr);
                    poblacion.setFechaFin(newEndDate);
                } catch (ParseException e) {
                    output.append("Invalid date format. Please enter the date in the format yyyy-MM-dd.\n");
                }
            }

            if (newNumBacteria != null) {
                poblacion.setNumBacterias(newNumBacteria);
            }

            if (newTemperature != null) {
                poblacion.setTemperatura(newTemperature);
            }

            if (newLuminosityStr != null) {
                Luminosidad newLuminosity = Luminosidad.valueOf(newLuminosityStr.toUpperCase());
                poblacion.setLuminosidad(newLuminosity);
            }

            if (newDosis != null) {
                poblacion.setDosisComida(newDosis);
            }

        } else {
            output.append("Invalid choice. Please enter a number between 1 and " + poblaciones.size() + "\n");
        }

        return output.toString();
    }

    /**
     * Returns a string representation of the Experimento.
     * @return A string representation of the Experimento
     */
    @Override
    public String toString() {
        return "Experimento{" +
                "idExperimento=" + idExperimento +
                ", nombreExp='" + nombreExp + '\'' +
                '}';
    }

    // Getters and setters for the Experimento's fields
    public String getPathArchivo() {
        return pathArchivo;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
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