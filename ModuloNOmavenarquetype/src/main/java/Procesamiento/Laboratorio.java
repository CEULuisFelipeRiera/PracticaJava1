package Procesamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laboratorio {
    public List<Experimento> experimentos;
    public Laboratorio() {
        this.experimentos = new ArrayList<>();
    }

    public void crearExperimento(int idExperimento, String nombreExp,  String pathArchivo, ArrayList<Poblacion> poblaciones) {
        // Create a new Experimento object
        Experimento newExperimento = new Experimento(idExperimento, nombreExp, pathArchivo, poblaciones);
        this.experimentos.add(newExperimento);
    }

    public String editarExperimento(int choice, String newIdStr, String newName) {
        StringBuilder output = new StringBuilder();

        // If the user's choice is valid, present a menu of fields that can be changed
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
    public boolean eliminarExperimentoPorId(int id) {
        for (Experimento experimento : experimentos) {
            if (experimento.getIdExperimento() == id) {
                experimentos.remove(experimento);
                return true;
            }
        }
        return false;
    }
}
