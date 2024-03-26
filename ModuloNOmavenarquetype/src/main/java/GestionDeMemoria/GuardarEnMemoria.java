package GestionDeMemoria;
import Procesamiento.*;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The GuardarEnMemoria class provides methods to save an Experimento object to a file.
 */
public class GuardarEnMemoria {

    /**
     * Saves an Experimento object to a file with a new name.
     * The details of the Experimento and its Poblacion objects are written to the file in a specific format.
     * Each detail is on a new line and is in the format "Detail name: Detail value".
     * The details of the Experimento are at the top of the file, followed by the details of each Poblacion.
     * Each Poblacion is separated by a blank line.
     * @param experimento The Experimento object to save
     */
    public static void guardarExperimentoComo(Experimento experimento) {
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

    /**
     * Saves an Experimento object to a file.
     * The details of the Experimento and its Poblacion objects are written to the file in a specific format.
     * Each detail is on a new line and is in the format "Detail name: Detail value".
     * The details of the Experimento are at the top of the file, followed by the details of each Poblacion.
     * Each Poblacion is separated by a blank line.
     * @param experimento The Experimento object to save
     * @return true if the Experimento was saved successfully; false otherwise
     */
    public static boolean guardar(Experimento experimento) {
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
            return true; // Return true if the file was saved correctly
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
            return false; // Return false if an exception was thrown
        }
    }
}