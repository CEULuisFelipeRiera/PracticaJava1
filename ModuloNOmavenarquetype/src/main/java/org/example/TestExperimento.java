package org.example;
import Procesamiento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TestExperimento {
    private Experimento experimento;
    private Poblacion poblacion1;
    private Poblacion poblacion2;

    @BeforeEach
    void setUp() {
        this.experimento = new Experimento(1, "Experimento 1", "Some String", new ArrayList<>());
        poblacion1 = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,250,150));
        poblacion2 = new Poblacion("Poblacion2", 2, new Date(), new Date(), 200, 37.0f, Luminosidad.BAJA, new Dosis(100,15,200,100));
    }

    @Test
    void shouldAddPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldNotAddNullPoblacion() {
        experimento.agregarPoblacion(null);
        assertFalse(experimento.getPoblaciones().contains(null));
    }

    @Test
    void shouldRemovePoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion1);
        assertFalse(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldNotRemoveNonExistentPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion2);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    @Test
    void shouldCreatePoblacion() {
        experimento.crearPoblacion("Poblacion3", 3, new Date(), new Date(), 300, 37.0f, Luminosidad.MEDIA, new Dosis(160,15,200,100));
        assertEquals(1, experimento.getPoblaciones().size());
        assertEquals("Poblacion3", experimento.getPoblaciones().get(0).getNombre());
    }
    @Test
    void shouldPrintPoblacionDetailsWhenMostrarPoblacionIsCalled() {
        // Add Poblacion objects to the experimento
        experimento.agregarPoblacion(poblacion1);
        experimento.agregarPoblacion(poblacion2);

        // Provide predefined input for the Scanner in mostrarPoblacion
        String input = "1\n"; // User chooses the first Poblacion
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call mostrarPoblacion
        experimento.mostrarPoblacion();

        // Check that the output contains the details of the Poblacion objects
        String expectedOutput1 = "1. " + poblacion1.getNombre() + " (ID: " + poblacion1.getIdPoblacion() + ")";
        String expectedOutput2 = "2. " + poblacion2.getNombre() + " (ID: " + poblacion2.getIdPoblacion() + ")";
    }
    @Test
    void shouldEditPoblacion() {
        // Set up the Experimento and Poblacion
        Experimento         experimento = new Experimento(1, "Experimento 1", "Some String", new ArrayList<>());
        Poblacion poblacion = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,250,150));
        experimento.agregarPoblacion(poblacion);

        // Call editarPoblacion with the new parameters
        String output = experimento.editarPoblacion(experimento, 1, "NewName", "2022-12-31", "2023-12-31", null, null, null, null);

        // Check that the Poblacion has been updated correctly
        assertEquals("NewName", poblacion.getNombre());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            assertEquals(formatter.parse("2022-12-31"), poblacion.getFechaInicio());
            assertEquals(formatter.parse("2023-12-31"), poblacion.getFechaFin());
        } catch (ParseException e) {
            fail("Failed to parse date");
        }

        // Check the output
        String expectedOutput = "";
        assertEquals(expectedOutput, output);
    }
    @Test
    void shouldSaveExperimentToFile() {
        // Set up the Experimento and Poblacion
        Experimento         experimento = new Experimento(1, "Experimento 1", "Some String", new ArrayList<>());; Poblacion poblacion = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,250,150));
        experimento.agregarPoblacion(poblacion);

        // Call guardarExperimentoEnArchivo
        experimento.guardarExperimentoComo(experimento);

        // Open the file and read its contents
        try {
            File file = new File(experimento.getNombreExp());
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
    @Test
    void shouldCreateExperimentoFromValidFile() {
        Experimento experimento = new Experimento(0, null, "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", null);
        Experimento loadedExperimento = experimento.leerExperimentoDesdeArchivo("C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt");
        assertNotNull(loadedExperimento);
        assertEquals(1, loadedExperimento.getIdExperimento());
        assertEquals("Experimento 1", loadedExperimento.getNombreExp());
    }

    @Test
    void shouldNotCreateExperimentoFromInvalidFileFormat() {
        Experimento experimento = new Experimento(0, null, "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txtt", null);
        assertThrows(IllegalArgumentException.class, () -> experimento.leerExperimentoDesdeArchivo("C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txt"));
    }
    @Test
    void shouldSaveExperimentoToValidFile() {
        Experimento experimento = new Experimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimentoNOvalido.txt", new ArrayList<>());
        assertDoesNotThrow(() -> experimento.guardar(experimento));
    }

    @Test
    void shouldSaveAndLoadExperimentoCorrectly() {
        Experimento experimento = new Experimento(1, "Experimento 1", "validFilePath", new ArrayList<>());
        experimento.guardar(experimento);
        Experimento loadedExperimento = experimento.leerExperimentoDesdeArchivo("validFilePath");
        assertEquals(experimento.getIdExperimento(), loadedExperimento.getIdExperimento());
        assertEquals(experimento.getNombreExp(), loadedExperimento.getNombreExp());
    }

}