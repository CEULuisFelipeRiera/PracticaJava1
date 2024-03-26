package Tests;
import Procesamiento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import GestionDeMemoria.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the GestionDeMemoria module.
 * It tests the functionality of saving and loading Experimento objects to and from files.
 */
class TestsGestionDeMemoria {
    private Experimento experimento;
    private Poblacion poblacion1;
    private Poblacion poblacion2;

    /**
     * This method sets up the common objects used in the test cases.
     * It is annotated with @BeforeEach, so it runs before each test case.
     */
    @BeforeEach
    void setUp() {
        this.experimento = new Experimento(1, "Experimento 1", "Some String", new ArrayList<>());
        poblacion1 = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150, 15, 130, 150));
        poblacion2 = new Poblacion("Poblacion2", 2, new Date(), new Date(), 200, 37.0f, Luminosidad.BAJA, new Dosis(100, 15, 150, 100));
    }

    /**
     * This test case checks if the guardarExperimentoComo method correctly saves an Experimento object to a file.
     * It creates a temporary file, an Experimento object, and a Poblacion object, and adds the Poblacion to the Experimento.
     * It then calls guardarExperimentoComo and checks if the contents of the file match the details of the Experimento and its Poblacion.
     */
    @Test
    void shouldSaveExperimentToFile() throws IOException {
        // Set up the Experimento and Poblacion
        File tempFile = File.createTempFile("tempFile", ".txt");
        Experimento experimento = new Experimento(1, "Experimento 1", tempFile.getAbsolutePath(), new ArrayList<>());
        Poblacion poblacion = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150, 15, 130, 150));
        experimento.agregarPoblacion(poblacion);

        // Call guardarExperimentoEnArchivo
        GuardarEnMemoria.guardarExperimentoComo(experimento);

        // Open the file and read its contents
        try {
            File file = new File(experimento.getPathArchivo());
            Scanner scanner = new Scanner(file);
            StringBuilder fileContents = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // Check that the contents of the file match the details of the Experimento and its Poblacion
            String expectedOutput = "Experimento ID: " + experimento.getIdExperimento() + "\n" +
                    "Experimento Name: " + experimento.getNombreExp() + "\n" +
                    "\n" +
                    "Poblacion ID: " + poblacion.getIdPoblacion() + "\n" +
                    "Poblacion Name: " + poblacion.getNombre() + "\n" +
                    "Start Date: " + poblacion.getFechaInicio() + "\n" +
                    "End Date: " + poblacion.getFechaFin() + "\n" +
                    "Number of Bacteria: " + poblacion.getNumBacterias() + "\n" +
                    "Temperature: " + poblacion.getTemperatura() + "\n" +
                    "Luminosity: " + poblacion.getLuminosidad() + "\n" +
                    "Food Dose: " + poblacion.getDosisComida() + "\n";
            assertEquals(expectedOutput, fileContents.toString());
        } catch (FileNotFoundException e) {
            fail("Failed to open the file");
        }
    }

    /**
     * This test case checks if the leerExperimentoDesdeArchivo method correctly loads an Experimento object from a valid file.
     * It creates an Experimento object and calls leerExperimentoDesdeArchivo with a valid file path.
     * It then checks if the loaded Experimento's id and name match the expected values.
     */
    @Test
    void shouldCreateExperimentoFromValidFile() {
        Experimento experimento = new Experimento(0, null, "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", null);
        Experimento loadedExperimento = LeerDeMemoria.leerExperimentoDesdeArchivo("C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt");
        assertNotNull(loadedExperimento);
        assertEquals(1, loadedExperimento.getIdExperimento());
        assertEquals("Experimento 1", loadedExperimento.getNombreExp());
    }

    /**
     * This test case checks if the leerExperimentoDesdeArchivo method throws an IllegalArgumentException when given an invalid file format.
     * It creates an Experimento object and calls leerExperimentoDesdeArchivo with an invalid file path.
     * It then checks if an IllegalArgumentException is thrown.
     */
    @Test
    void shouldNotCreateExperimentoFromInvalidFileFormat() {
        Experimento experimento = new Experimento(0, null, "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txt", null);
        assertThrows(IllegalArgumentException.class, () -> LeerDeMemoria.leerExperimentoDesdeArchivo("C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txt"));
    }

    /**
     * This test case checks if the guardar method does not throw an exception when given a valid Experimento object.
     * It creates an Experimento object and calls guardar.
     * It then checks if no exception is thrown.
     */
    @Test
    void shouldSaveExperimentoToValidFile() {
        Experimento experimento = new Experimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txt", new ArrayList<>());
        assertDoesNotThrow(() -> GuardarEnMemoria.guardar(experimento));
    }

    /**
     * This test case checks if the guardar and leerExperimentoDesdeArchivo methods work correctly together.
     * It creates an Experimento object, calls guardar, and then calls leerExperimentoDesdeArchivo.
     * It then checks if the loaded Experimento's id and name match the original Experimento's id and name.
     */
    @Test
    void shouldSaveAndLoadExperimentoCorrectly() {
        Experimento experimento = new Experimento(1, "Experimento 1", "validFilePath", new ArrayList<>());
        GuardarEnMemoria.guardar(experimento);
        Experimento loadedExperimento = LeerDeMemoria.leerExperimentoDesdeArchivo("validFilePath");
        assertEquals(experimento.getIdExperimento(), loadedExperimento.getIdExperimento());
        assertEquals(experimento.getNombreExp(), loadedExperimento.getNombreExp());
    }
}