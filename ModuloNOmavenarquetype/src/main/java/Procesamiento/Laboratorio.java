package Procesamiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laboratorio {
    public List<Experimento> experimentos;

    public void crearExperimento(int idExperimento, String nombreExp, ArrayList<Poblacion> poblaciones) {
        // Create a new Experimento object
        Experimento newExperimento = new Experimento(idExperimento, nombreExp, poblaciones);
        this.experimentos.add(newExperimento);
    }

    public void eliminarExperimento(Experimento experimento) {
        this.experimentos.remove(experimento);
    }
    public void mostrarExperimento(Experimento experimento) {
        System.out.println(experimento.toString());

        // Print the details of each Poblacion in the Experimento
        for (Poblacion poblacion : experimento.getPoblaciones()) {
            System.out.println(poblacion.toString());
        }
    }
    public  void editarExperimento() {
        Scanner scanner = new Scanner(System.in);

        // Print the list of Poblacion objects and ask the user to choose one
        for (int i = 0; i < experimentos.size(); i++) {
            System.out.println((i + 1) + ". " + experimentos.get(i).getIdExperimento() + " (ID: " + experimentos.get(i).getNombreExp() + ")");
        }
        System.out.println("Enter the number of the Experiment you want to edit:");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= experimentos.size()) {
            Experimento experimento1 = experimentos.get(choice - 1);
            scanner.nextLine(); // Consume the newline left-over

            System.out.println("Do you want to change the Id? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new Id for the experiment:");
                int id = Integer.parseInt(scanner.nextLine());
                experimento1.setIdExperimento(id);
            }

            System.out.println("Do you want to change the name? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new name for the experiment:");
                String newName2 = scanner.nextLine();
                experimento1.setNombreExp(newName2);
            }

            System.out.println("Do you want to edit the Poblaciones? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                editarExperimento();
            }

        } else {
            System.out.println("Invalid choice. Please enter a number between 1 and " + experimentos.size());
        }
    }

}
