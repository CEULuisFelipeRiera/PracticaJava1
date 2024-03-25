package org.example;
import Procesamiento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class TestExperimento {
    private Experimento experimento;
    private Poblacion poblacion1;
    private Poblacion poblacion2;

    @BeforeEach
    void setUp() {
        experimento = new Experimento(1, "Experimento 1", null);
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
        Experimento experimento = new Experimento(1, "Experimento 1", null);
        Poblacion poblacion = new Poblacion("Poblacion1", 1, new Date(), new Date(), 100, 37.0f, Luminosidad.ALTA, new Dosis(150,15,250,150));
        experimento.agregarPoblacion(poblacion);

        // Set up the simulated user input
        String input = "1\nyes\nNewName\nyes\n2022-12-31\nyes\n2023-12-31\nno\nno\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Set up the ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call editarPoblacion
        experimento.editarPoblacion();

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
        String expectedOutput = "1. NewName (ID: 1)\nEnter the number of the Poblacion you want to edit:\nDo you want to change the name? (yes/no)\nEnter the new name for the Poblacion:\nDo you want to change the start date? (yes/no)\nEnter the new start date for the Poblacion (yyyy-MM-dd):\nDo you want to change the end date? (yes/no)\nEnter the new end date for the Poblacion (yyyy-MM-dd):\nDo you want to change the number of bacteria? (yes/no)\nDo you want to change the temperature? (yes/no)\nDo you want to change the luminosity? (yes/no)\nDo you want to change the food dose? (yes/no)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}