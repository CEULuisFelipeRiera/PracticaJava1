package Procesamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import GestionDeMemoria.*;

/**
 * The Laboratorio class represents a laboratory in which experiments are conducted.
 * It contains a list of Experimento objects, each representing an individual experiment.
 */
public class Laboratorio {
    // The name of the laboratory
    public String labName;
    // The list of experiments conducted in the laboratory
    public List<Experimento> experimentos;

    /**
     * Constructs a new Laboratorio with the given name.
     * @param labName The name of the laboratory
     */
    public Laboratorio(String labName) {
        this.labName = labName;
        this.experimentos = new ArrayList<>();
    }

    /**
     * Creates a new Experimento and adds it to the list of experiments.
     * @param idExperimento The ID of the new experiment
     * @param nombreExp The name of the new experiment
     * @param pathArchivo The path to the file associated with the new experiment
     * @param poblaciones The list of populations involved in the new experiment
     */
    public void crearExperimento(int idExperimento, String nombreExp,  String pathArchivo, ArrayList<Poblacion> poblaciones) {
        Experimento newExperimento = new Experimento(idExperimento, nombreExp, pathArchivo, poblaciones);
        this.experimentos.add(newExperimento);
    }

    /**
     * Edits an existing Experimento based on the user's choice.
     * @param choice The index of the experiment to be edited
     * @param newIdStr The new ID for the experiment
     * @param newName The new name for the experiment
     * @return A string indicating the result of the operation
     */
    public String editarExperimento(int choice, String newIdStr, String newName) {
        StringBuilder output = new StringBuilder();

        if (choice > 0 && choice <= experimentos.size()) {
            Experimento experimento = experimentos.get(choice - 1);

            if (newIdStr != null) {
                try {
                    int newId = Integer.parseInt(newIdStr);
                    experimento.setIdExperimento(newId);
                } catch (NumberFormatException e) {
                    output.append("Invalid ID format. Please enter a valid integer.\n");
                }
            }

            if (newName != null) {
                experimento.setNombreExp(newName);
            }

        } else {
            output.append("Invalid choice. Please enter a number between 1 and " + experimentos.size() + "\n");
        }

        return output.toString();
    }

    /**
     * Deletes an Experimento from the list of experiments based on its ID.
     * @param id The ID of the experiment to be deleted
     * @return A boolean indicating whether the operation was successful
     */
    public boolean eliminarExperimentoPorId(int id) {
        for (Experimento experimento : experimentos) {
            if (experimento.getIdExperimento() == id) {
                experimentos.remove(experimento);
                return true;
            }
        }
        return false;
    }

    // Getters and setters for the Laboratorio's fields
    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public List<Experimento> getExperimentos() {
        return experimentos;
    }

    public void setExperimentos(List<Experimento> experimentos) {
        this.experimentos = experimentos;
    }
}
