package GestionDeMemoria;

import Procesamiento.Dosis;
import Procesamiento.Experimento;
import Procesamiento.Luminosidad;
import Procesamiento.Poblacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

    /**
     * The LeerDeMemoria class provides a method to read an Experimento object from a file.
     */
    public class LeerDeMemoria {

        /**
         * Reads an Experimento object from a file.
         * The file should contain the details of the Experimento and its Poblacion objects in a specific format.
         * Each detail should be on a new line and should be in the format "Detail name: Detail value".
         * The details of the Experimento should be at the top of the file, followed by the details of each Poblacion.
         * Each Poblacion should be separated by a blank line.
         * @param pathArchivo The path of the file to read the Experimento from
         * @return The Experimento object read from the file, or null if an error occurred
         * @throws IllegalArgumentException If the file format is incorrect
         */
        public static Experimento leerExperimentoDesdeArchivo(String pathArchivo) {
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
                    scanner.nextLine();
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
                    int part2 = Integer.parseInt(foodDoseParts[1]);
                    double part3 = Double.parseDouble(foodDoseParts[2]);
                    double part4 = Double.parseDouble(foodDoseParts[3]);
                    Dosis dosis = new Dosis(part1, part2, part3, part4);
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
    }
