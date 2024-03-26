package org.example;
import java.util.Scanner;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Abrir un archivo que contenga un experimento");
            System.out.println("2. Crear un nuevo experimento");
            System.out.println("3. Crear una población de bacterias y añadirla al experimento actual");
            System.out.println("4. Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
            System.out.println("5. Borrar una población de bacterias del experimento actual");
            System.out.println("6. Ver información detallada de una población de bacterias del experimento actual");
            System.out.println("7. Guardar");
            System.out.println("8. Guardar como");
            System.out.println("0. Salir");

            System.out.print("Ingrese la opción deseada: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Opción 1 seleccionada: Abrir un archivo que contenga un experimento");

                    break;
                case 2:
                    System.out.println("Opción 2 seleccionada: Crear un nuevo experimento");
                    // Implement your logic for creating a new experiment
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Crear una población de bacterias y añadirla al experimento actual");
                    // Implement your logic for creating and adding a bacteria population
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
                    // Implement your logic for displaying names of bacteria populations
                    break;
                case 5:
                    System.out.println("Opción 5 seleccionada: Borrar una población de bacterias del experimento actual");
                    // Implement your logic for deleting a bacteria population
                    break;
                case 6:
                    System.out.println("Opción 6 seleccionada: Ver información detallada de una población de bacterias del experimento actual");
                    // Implement your logic for displaying detailed information of a bacteria population
                    break;
                case 7:
                    System.out.println("Opción 7 seleccionada: Guardar");
                    // Implement your logic for saving the current experiment
                    break;
                case 8:
                    System.out.println("Opción 8 seleccionada: Guardar como");
                    // Implement your logic for saving the current experiment with a new file name
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}


/*    public void editarPoblacion(Experimento experimento) {
        Scanner scanner = new Scanner(System.in);

        // Print the list of Poblacion objects and ask the user to choose one
        for (int i = 0; i < experimento.poblaciones.size(); i++) {
            System.out.println((i + 1) + ". " + experimento.poblaciones.get(i).getNombre() + " (ID: " + experimento.poblaciones.get(i).getIdPoblacion() + ")");
        }
        System.out.println("Enter the number of the Poblacion you want to edit:");
        int choice = scanner.nextInt();

        // If the user's choice is valid, present a menu of fields that can be changed
        if (choice > 0 && choice <= experimento.poblaciones.size()) {
            Poblacion poblacion = experimento.poblaciones.get(choice - 1);
            scanner.nextLine(); // Consume the newline left-over

            System.out.println("Do you want to change the name? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new name for the Poblacion:");
                String newName = scanner.nextLine();
                poblacion.setNombre(newName);
            }


            System.out.println("Do you want to change the start date? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new start date for the Poblacion (yyyy-MM-dd):");
                String newStartDateStr = scanner.nextLine();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date newStartDate = formatter.parse(newStartDateStr);
                    poblacion.setFechaInicio(newStartDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                }
            }

            System.out.println("Do you want to change the end date? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new end date for the Poblacion (yyyy-MM-dd):");
                String newEndDateStr = scanner.nextLine();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date newEndDate = formatter.parse(newEndDateStr);
                    poblacion.setFechaFin(newEndDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                }
            }

            System.out.println("Do you want to change the number of bacteria? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new number of bacteria for the Poblacion:");
                int newNumBacteria = scanner.nextInt();
                poblacion.setNumBacterias(newNumBacteria);
            }

            System.out.println("Do you want to change the temperature? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new temperature for the Poblacion:");
                float newTemperature = scanner.nextFloat();
                poblacion.setTemperatura(newTemperature);
            }

            System.out.println("Do you want to change the luminosity? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new luminosity for the Poblacion (ALTA, MEDIA, BAJA):");
                Luminosidad newLuminosity = Luminosidad.valueOf(scanner.nextLine().toUpperCase());
                poblacion.setLuminosidad(newLuminosity);
            }

            System.out.println("Do you want to change the food dose? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter the new food dose for the Poblacion:");
                System.out.println("Enter the initial amount of food:");
                double newInitialAmount = scanner.nextDouble();
                System.out.println("Enter the day of the increment:");
                double newDayIncrement = scanner.nextDouble();
                System.out.println("Enter the amount of the increment:");
                double newAmountIncrement = scanner.nextDouble();
                System.out.println("Enter the final amount of food:");
                double newFinalAmount = scanner.nextDouble();
                Dosis newDosis = new Dosis(newInitialAmount, newDayIncrement, newAmountIncrement, newFinalAmount);
                poblacion.setDosisComida(newDosis);
            }

        } else {
            System.out.println("Invalid choice. Please enter a number between 1 and " + poblaciones.size());
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
    }*/