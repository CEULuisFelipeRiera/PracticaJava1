package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Procesamiento.*;
import java.util.ArrayList;

/**
 * This class contains test cases for the Laboratorio class.
 * It tests the functionality of creating, editing, and deleting Experimento objects in a Laboratorio.
 */
public class TestsLaboratorio {
    /**
     * The Laboratorio object that will be used in the test cases.
     */
    private Laboratorio laboratorio;

    /**
     * Sets up the Laboratorio object before each test.
     */
    @BeforeEach
    void setUp() {
        this.laboratorio = new Laboratorio("Test Lab");
    }

    /**
     * Test case for the method crearExperimento in the Laboratorio class.
     * This test case verifies that a new experiment is correctly added to the laboratory.
     * The test case follows these steps:
     * 1. Calls the method crearExperimento with valid parameters.
     * 2. Checks that the size of the experimentos list in the Laboratorio object has increased by 1.
     */
    @Test
    void shouldAddExperimentoWhenCreatingNewOne() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertEquals(1, laboratorio.experimentos.size());
    }

    /**
     * Test case for the method editarExperimento in the Laboratorio class.
     * This test case verifies that the experiment is not changed when an invalid choice is provided.
     * The test case follows these steps:
     * 1. Calls the method crearExperimento to create a new experiment.
     * 2. Calls the method editarExperimento with an invalid choice.
     * 3. Checks that the experiment's id and name have not changed.
     */
    @Test
    void shouldNotChangeExperimentoWhenEditingWithInvalidChoice() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        String result = laboratorio.editarExperimento(2, "2", "Experimento 2");
        assertTrue(result.contains("Invalid choice"));
        assertEquals(1, laboratorio.experimentos.get(0).getIdExperimento());
        assertEquals("Experimento 1", laboratorio.experimentos.get(0).getNombreExp());
    }

    /**
     * Test case for the method editarExperimento in the Laboratorio class.
     * This test case verifies that the experiment is correctly changed when a valid choice is provided.
     * The test case follows these steps:
     * 1. Calls the method crearExperimento to create a new experiment.
     * 2. Calls the method editarExperimento with a valid choice.
     * 3. Checks that the experiment's id and name have been updated.
     */
    @Test
    void shouldChangeExperimentoWhenEditingWithValidChoice() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        laboratorio.editarExperimento(1, "2", "Experimento 2");
        assertEquals(2, laboratorio.experimentos.get(0).getIdExperimento());
        assertEquals("Experimento 2", laboratorio.experimentos.get(0).getNombreExp());
    }

    /**
     * Test case for the method eliminarExperimentoPorId in the Laboratorio class.
     * This test case verifies that the experiment is not removed when an invalid id is provided.
     * The test case follows these steps:
     * 1. Calls the method crearExperimento to create a new experiment.
     * 2. Calls the method eliminarExperimentoPorId with an invalid id.
     * 3. Checks that the size of the experimentos list in the Laboratorio object has not changed.
     */
    @Test
    void shouldNotRemoveExperimentoWhenDeletingWithInvalidId() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertFalse(laboratorio.eliminarExperimentoPorId(2));
        assertEquals(1, laboratorio.experimentos.size());
    }

    /**
     * Test case for the method eliminarExperimentoPorId in the Laboratorio class.
     * This test case verifies that the experiment is correctly removed when a valid id is provided.
     * The test case follows these steps:
     * 1. Calls the method crearExperimento to create a new experiment.
     * 2. Calls the method eliminarExperimentoPorId with a valid id.
     * 3. Checks that the size of the experimentos list in the Laboratorio object has decreased by 1.
     */
    @Test
    void shouldRemoveExperimentoWhenDeletingWithValidId() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertTrue(laboratorio.eliminarExperimentoPorId(1));
        assertEquals(0, laboratorio.experimentos.size());
    }
}