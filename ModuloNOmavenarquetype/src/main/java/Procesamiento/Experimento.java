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
    public void imprimirNombresPoblaciones() {
        for (Poblacion poblacion : this.poblaciones) {
            System.out.println(poblacion.getNombre());
        }
    }
    public void editarPoblacion(Experimento experimento) {
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

    public void guardarExperimentoEnArchivo(Experimento experimento) {
        String nombreArchivo = experimento.getNombreExp();
        try {
            FileWriter writer = new FileWriter(nombreArchivo);

            // Write the details of the Experimento
            writer.write("Experimento ID: " + experimento.getIdExperimento() + "\n");
            writer.write("Experimento Name: " + experimento.getNombreExp() + "\n");

            // Write the details of each Poblacion
            for (Poblacion poblacion : experimento.getPoblaciones()) {
                writer.write("\n");
                writer.write("Poblacion ID: " + poblacion.getIdPoblacion() + "\n");
                writer.write("Poblacion Name: " + poblacion.getNombre() + "\n");
                writer.write("Start Date: " + poblacion.getFechaInicio() + "\n");
                writer.write("End Date: " + poblacion.getFechaFin() + "\n");
                writer.write("Number of Bacteria: " + poblacion.getNumBacterias() + "\n");
                writer.write("Temperature: " + poblacion.getTemperatura() + "\n");
                writer.write("Luminosity: " + poblacion.getLuminosidad() + "\n");
                writer.write("Food Dose: " + poblacion.getDosisComida() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    public void leerExperimentoDesdeArchivo(String pathArchivo) {
        try {
            File file = new File(pathArchivo);
            Scanner scanner = new Scanner(file);

            // Read the details of the Experimento
            String experimentoId = scanner.nextLine().split(": ")[1];
            String experimentoName = scanner.nextLine().split(": ")[1];

            // Read the details of each Poblacion
            while (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the empty line
                String poblacionId = scanner.nextLine().split(": ")[1];
                String poblacionName = scanner.nextLine().split(": ")[1];
                String startDate = scanner.nextLine().split(": ")[1];
                String endDate = scanner.nextLine().split(": ")[1];
                String numBacterias = scanner.nextLine().split(": ")[1];
                String temperatura = scanner.nextLine().split(": ")[1];
                String luminosidad = scanner.nextLine().split(": ")[1];
                String foodDose = scanner.nextLine().split(": ")[1];

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Experimento{" +
                "idExperimento=" + idExperimento +
                ", nombreExp='" + nombreExp + '\'' +
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