package Tests;
import Procesamiento.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class contains a test for the Dosis class.
 * It tests the functionality of creating a Dosis object and calculating the daily dose.
 */
public class TestDosis {
    // BufferedReader to read input from the console
    public static BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

    /**
     * The main method which is the entry point for the test.
     * It prompts the user to enter the parameters for a Dosis object, creates the object, and calculates the daily dose.
     * @param args Command line arguments. Not used in this method.
     * @throws IOException If an input or output exception occurred
     */
    public static void main(String[] args) throws IOException {
        try {
            // Prompt the user to enter the parameters for a Dosis object
            System.out.println("Creando dosis");
            System.out.println("introduzca la cantidad inicial: ");
            double cantidadInicial = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca el dia de incremento: ");
            int diaDeIncremento = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca la cantidad de incremento: ");
            double cantidadIncremento = Integer.parseInt(bufr.readLine());
            System.out.println("introduzca la cantidad final: ");
            double cantidadFinal = Integer.parseInt(bufr.readLine());

            // Create a Dosis object with the entered parameters
            Dosis dosistest = new Dosis(cantidadInicial, diaDeIncremento, cantidadIncremento, cantidadFinal);

            // Calculate the daily dose
            dosistest.calcularDosisDiaria(dosistest);

        } catch (IOException e) {
            // Print an error message if an input or output exception occurred
            System.out.println("Error en la entrada de datos");
        }
    }
}



