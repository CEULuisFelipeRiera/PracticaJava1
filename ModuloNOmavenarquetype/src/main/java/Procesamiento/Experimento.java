package Procesamiento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Experimento {
    public int idExperimento;
    public String nombreExp;
    public String pathArchivo;
    public ArrayList<Poblacion> poblaciones;
    

    public Experimento(int idExperimento, String nombreExp, String pathArchivo, List<Poblacion> poblaciones) {
        this.idExperimento = idExperimento;
        this.nombreExp = nombreExp;
        this.pathArchivo = pathArchivo;
        this.poblaciones = new ArrayList<>();
    }

    public void agregarPoblacion(Poblacion poblacion) {
        this.poblaciones.add(poblacion);
    }

    public void crearPoblacion(String nombre, int idPoblacion, Date fechaInicio, Date fechaFin, int numBacterias, float temperatura, Luminosidad luminosidad, Dosis dosisComida) {
        Poblacion poblacion1 = new Poblacion(nombre, idPoblacion, fechaInicio, fechaFin, numBacterias, temperatura, luminosidad, dosisComida);
        agregarPoblacion(poblacion1);
    }

    public boolean borrarPoblacion(Poblacion poblacion) {
        return this.poblaciones.remove(poblacion);
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

    public void guardarExperimentoComo(Experimento experimento) {
        String nombreArchivo = experimento.getPathArchivo();
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
    public Experimento leerExperimentoDesdeArchivo(String pathArchivo) {
        Experimento experimento = null;
        try {
            File file = new File(pathArchivo);
            Scanner scanner = new Scanner(file);

            // Read the details of the Experimento
            int experimentoId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
            String experimentoName = scanner.nextLine().split(": ")[1];

            // Create the Experimento object
            experimento = new Experimento(experimentoId, experimentoName, pathArchivo, new ArrayList<>());

            // Read the details of each Poblacion
            while (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the empty line
                int poblacionId = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                String poblacionName = scanner.nextLine().split(": ")[1];
                String startDate = scanner.nextLine().split(": ")[1];
                String endDate = scanner.nextLine().split(": ")[1];
                int numBacterias = Integer.parseInt(scanner.nextLine().split(": ")[1]);
                float temperatura = Float.parseFloat(scanner.nextLine().split(": ")[1]);
                Luminosidad luminosidad = Luminosidad.valueOf(scanner.nextLine().split(": ")[1].toUpperCase());
                String foodDose = scanner.nextLine().split(": ")[1];

                // Parse the foodDose string into four double values
                String[] foodDoseParts = foodDose.split(",");
                double part1 = Double.parseDouble(foodDoseParts[0]);
                double part2 = Double.parseDouble(foodDoseParts[1]);
                double part3 = Double.parseDouble(foodDoseParts[2]);
                double part4 = Double.parseDouble(foodDoseParts[3]);

                // Create the Dosis object
                Dosis dosis = new Dosis(part1, part2, part3, part4);

                // Create the Poblacion object
                Poblacion poblacion = new Poblacion(poblacionName, poblacionId, new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate), numBacterias, temperatura, luminosidad, dosis);

                // Add the Poblacion to the Experimento
                experimento.agregarPoblacion(poblacion);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while searching the file.");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("An error occurred while parsing the date.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The file format is incorrect.", e);
        }
        return experimento;
    }

    public void guardar(Experimento experimento) {
        String nombreArchivo = experimento.getPathArchivo();
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
    @Override
    public String toString() {
        return "Experimento{" +
                "idExperimento=" + idExperimento +
                ", nombreExp='" + nombreExp + '\'' +
                '}';
    }

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