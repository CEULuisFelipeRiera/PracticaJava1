package Procesamiento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.io.*;

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
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < poblaciones.size(); i++) {
            System.out.println((i + 1) + ". " + poblaciones.get(i).getNombre() + " (ID: " + poblaciones.get(i).getIdPoblacion() + ")");
        }
        System.out.println("Enter the number of the Poblacion you want to see:");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= poblaciones.size()) {
            System.out.println(poblaciones.get(choice - 1));
        } else {
            System.out.println("Invalid choice. Please enter a number between 1 and " + poblaciones.size());
        }
    }
    public void editarPoblacion() {
        Scanner scanner = new Scanner(System.in);

        // Print the list of Poblacion objects and ask the user to choose one
        for (int i = 0; i < poblaciones.size(); i++) {
            System.out.println((i + 1) + ". " + poblaciones.get(i).getNombre() + " (ID: " + poblaciones.get(i).getIdPoblacion() + ")");
        }
        System.out.println("Enter the number of the Poblacion you want to edit:");
        int choice = scanner.nextInt();

        // If the user's choice is valid, present a menu of fields that can be changed
        if (choice > 0 && choice <= poblaciones.size()) {
            Poblacion poblacion = poblaciones.get(choice - 1);
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