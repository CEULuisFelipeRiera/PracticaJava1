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
 * This class contains test cases for the Experimento class.
 * It tests the functionality of adding, removing, creating, and editing Poblacion objects in an Experimento.
 */
class TestExperimento {
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
        poblacion1 = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,130,150));
        poblacion2 = new Poblacion("Poblacion2", 2, new Date(), new Date(), 200, 37.0f, Luminosidad.BAJA, new Dosis(100,15,100,100));
    }

    /**
     * This test case checks if the agregarPoblacion method correctly adds a Poblacion to an Experimento.
     */
    @Test
    void shouldAddPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    /**
     * This test case checks if the agregarPoblacion method does not add a null Poblacion to an Experimento.
     */
    @Test
    void shouldNotAddNullPoblacion() {
        experimento.agregarPoblacion(null);
        assertFalse(experimento.getPoblaciones().contains(null));
    }

    /**
     * This test case checks if the borrarPoblacion method correctly removes a Poblacion from an Experimento.
     */
    @Test
    void shouldRemovePoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion1);
        assertFalse(experimento.getPoblaciones().contains(poblacion1));
    }

    /**
     * This test case checks if the borrarPoblacion method does not remove a non-existent Poblacion from an Experimento.
     */
    @Test
    void shouldNotRemoveNonExistentPoblacion() {
        experimento.agregarPoblacion(poblacion1);
        experimento.borrarPoblacion(poblacion2);
        assertTrue(experimento.getPoblaciones().contains(poblacion1));
    }

    /**
     * This test case checks if the crearPoblacion method correctly creates a Poblacion and adds it to an Experimento.
     */
    @Test
    void shouldCreatePoblacion() {
        experimento.crearPoblacion("Poblacion3", 3, new Date(), new Date(), 300, 37.0f, Luminosidad.MEDIA, new Dosis(160,15,100,100));
        assertEquals(1, experimento.getPoblaciones().size());
        assertEquals("Poblacion3", experimento.getPoblaciones().get(0).getNombre());
    }

    /**
     * This test case checks if the editarPoblacion method correctly edits a Poblacion in an Experimento.
     */
    @Test
    void shouldEditPoblacion() {
        // Set up the Experimento and Poblacion
        Experimento         experimento = new Experimento(1, "Experimento 1", "Some String", new ArrayList<>());
        Poblacion poblacion = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,130,150));
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

    /**
     * This test case checks if the mostrarPoblacion method correctly returns a string representation of a Poblacion in an Experimento.
     */
    void mostrarPoblacion() {
        // Create a new Poblacion
        Poblacion poblacion = new Poblacion("Test Poblacion", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(100, 15, 200, 100));

        // Create a new Experimento and add it to the Laboratorio
        Experimento experimento = new Experimento(1, "Test Experimento", "path/to/file", new ArrayList<>());
        Laboratorio laboratorio = new Laboratorio("Test Lab");
        laboratorio.getExperimentos().add(experimento);

        // Add the Poblacion to the Experimento
        experimento.getPoblaciones().add(poblacion);

        // Call the method we want to test
        String result = experimento.mostrarPoblacion("Test Poblacion");

        // Check the result
        assertEquals("Poblacion{" +
                "nombre='Test Poblacion'" +
                ", fechaInicio=" + poblacion.getFechaInicio() +
                ", fechaFin=" + poblacion.getFechaFin() +
                ", numBacteriasIniciales=" + poblacion.getNumBacterias() +
                ", temperatura=" + poblacion.getTemperatura() +
                ", luminosidad='" + poblacion.getLuminosidad() + '\'' +
                ", dosisComida=" + poblacion.getDosisComida() +
                '}', result);
    }
}