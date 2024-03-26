package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Procesamiento.*;
import java.util.ArrayList;

public class TestsLaboratorio {
    private Laboratorio laboratorio;

    @BeforeEach
    void setUp() {
        laboratorio = new Laboratorio();
    }

    @Test
    void shouldAddExperimentoWhenCreatingNewOne() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertEquals(1, laboratorio.experimentos.size());
    }

    @Test
    void shouldNotChangeExperimentoWhenEditingWithInvalidChoice() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        String result = laboratorio.editarExperimento(2, "2", "Experimento 2");
        assertTrue(result.contains("Invalid choice"));
        assertEquals(1, laboratorio.experimentos.get(0).getIdExperimento());
        assertEquals("Experimento 1", laboratorio.experimentos.get(0).getNombreExp());
    }

    @Test
    void shouldChangeExperimentoWhenEditingWithValidChoice() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        laboratorio.editarExperimento(1, "2", "Experimento 2");
        assertEquals(2, laboratorio.experimentos.get(0).getIdExperimento());
        assertEquals("Experimento 2", laboratorio.experimentos.get(0).getNombreExp());
    }

    @Test
    void shouldNotRemoveExperimentoWhenDeletingWithInvalidId() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertFalse(laboratorio.eliminarExperimentoPorId(2));
        assertEquals(1, laboratorio.experimentos.size());
    }

    @Test
    void shouldRemoveExperimentoWhenDeletingWithValidId() {
        laboratorio.crearExperimento(1, "Experimento 1", "C:\\Users\\pipe\\Documents\\Programacion CEU\\Java\\IntelliJ\\Segundo Curso\\Archivos de Texto Practica 1\\experimento1.txt", new ArrayList<>());
        assertTrue(laboratorio.eliminarExperimentoPorId(1));
        assertEquals(0, laboratorio.experimentos.size());
    }
}